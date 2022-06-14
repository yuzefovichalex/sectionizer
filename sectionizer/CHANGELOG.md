*Sectionizer changelog by Alexander Yuzefovich*

### 1.0.1
- Fixed an issue (crash) when removing items from SectionsAdapter caused by invalid item position (-1)
- Added possibility to override onBindViewHolder method

### 1.1.0
- Reworked Section's adapter state restore. The *AdapterPolicy* of Section was removed. Now all the internal lists updates are controlled by SectionsAdapter (**submitList** method).
- Reworked logic of start/stop data requests. Now Section's data request is triggered in **onBindViewHolder** and stopped in **onViewRecycled**.
- Removed unnecessary *areSectionsStatic* param from the SectionsAdapter since all the content compare behavior is inside Section.