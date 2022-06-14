# Sectionizer

[![](https://jitpack.io/v/yuzefovichalex/sectionizer.svg)](https://jitpack.io/#yuzefovichalex/sectionizer)

Quite often we may have the task of creating a list, each element of which is a set of data that includes a nested list. Often we have to write quite a lot of boilerplate code to do this. Sectionizer takes control of the basic tasks of controlling root and child lists, and allows you to speed up sectional list creation.

![](images/sample.png)

## How to get

Add the repository in root build.gradle:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency in module build.gradle:

```groovy
dependencies {
    implementation 'com.github.yuzefovichalex:sectionizer:<latest-version>'
}
```

## Usage

Let's look at an example. Suppose we have several coffee lists, divided into sections, each with its own unique name. Thus, each section displays the name and the list of coffee.

### 1. Model

For example, the simple **Coffee** model:

```kotlin
data class Coffee(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("image")
    val imageUrl: String,

    @SerializedName("cost")
    val cost: Float
)
```

### 2. Model adapter

Next, let's create the most common adapter for the Coffee list. The only difference is that the adapter implements two methods of the **SectionAdapter** interface:

```kotlin
class CoffeeAdapter : 
    ListAdapter<Coffee, CoffeeAdapter.ViewHolder>(DiffUtilCallback),
    SectionAdapter<Coffee> 
{

    // ... Usual RecyclerView setup ...

    // From SectonAdapter
    fun getLatestSnapshot(): List<Coffee> = currentList

    // From SectonAdapter
    fun restoreFromSnapshot(snapshot: List<Coffee>) {
        submitList(snapshot)
    }


    class ViewHolder(
        private val binding: ItemCoffeeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        // ... Any bind logic ...

    }

}
```

### 3. Section

Now let's create our section. Here we need to define the internal list **Adapter**, the **DataController**, which will start and stop receiving/listening data, as well as the logic for comparing sections with each other:

```kotlin
class CoffeeTimeSection(
    val name: String,
    override val adapter: CoffeeAdapter,
    private val dataExecutor: DataExecutor<Coffee>
) : Section<Coffee, CoffeeAdapter>() {

    override val dataController: DataController = object : DataController {
        override fun startDataRequests() {
            dataExecutor.execute()
        }

        override fun stopDataRequests() {
            // Stop any listeners or stay empty if we execute one-shot request
        }
    }

    override fun isTheSameWith(another: Section<*, *>): Boolean =
        another is CoffeeTimeSection && name == another.name

    // Content of the section is list, so So let's leave the list comparison to the CoffeeAdapter
    override fun isContentTheSameWith(another: Section<*, *>): Boolean = false

}
```

Keep in mind that you are in control of the data transfer to the adapter. In the example you can see that the section accepts the DataExecutor. In turn, the DataExecutor can accept some kind of DataListener interface, which will listen for data from the DataExecutor. This interface can be implemented directly by your Adapter, so data from the DataExecutor will be passed directly to the Adapter. You can see an example of this implementation in the sample app of the project.

### 4. Section adapter

The last thing we need to do is to create our implementation of the **SectionsAdapter**. Here we just need to override the **onCreateViewHolder** method, and create a ViewHolder where we need to specify our internal list as well as the logic for filling our View with data via the **bind** method:

```kotlin
class SimpleSectionsAdapter :
    SectionsAdapter<CoffeeTimeSection, SimpleSectionsAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHorizontalSectionBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    // No bind method

    class ViewHolder(
        private val binding: ItemCoffeeTimeSectionBinding
    ) : SectionsAdapter.ViewHolder<CoffeeTimeSection>(binding.root) {

        override val sectionRV: RecyclerView
            get() = binding.itemList

        override fun bind(section: CoffeTimeSection) {
            with(binding) {
                name.text = section.name
            }
        }

    }

}
```

Or we can do it via Kotlin extension functions:

```kotlin
sectionsAdapter<Coffee> { parent ->
    val binding = ItemCoffeeBinding.inflate(layoutInflater, parent, false)
 
    sectionViewHolder(
        binding.list,
        binding.root
    ) { section ->
        with(binding) {
            name.text = section.name
        }
    }
 }
```

That's all :) You can find more advanced examples, including sections with different types of lists (vertical and horizontal) in the sample app of this project.

## Version info

The latest lib version: **1.1.0**

Minimum SDK: **21**

Target SDK: **31**

## License

    Copyright 2020 Alexander Yuzefovich.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
