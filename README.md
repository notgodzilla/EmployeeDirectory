## Build tools & versions used
Build Tools 34.0.0
Compile SDK 33
Moshi 1.8.0
Coil 2.0.0

## Steps to run the app
Build apk then run on device. App will pull from the 'valid' employee endpoint by default. The pull to refresh functionality also will remember the last dataset queried when refreshing. The 'malformed' and 'empty' employee endpoints can be selected from the drop down menu on the top right corner.

## NOTES
This was my take home project for Block in which I was to build an EmployeeDirectory. I was not chosen to move forward but I decided to iterate on this project and add more features. 

## PLANNED FEATURES 
- Unit Tests
- Make use of dependency injection and the Use Case pattern to modularize the app which will also help with implementing unit tests
- Factor out use of deprecated use of options menu in EmployeeDirectoryFragment
- Handle endpoint with malformed dataset by excluding that employee and displaying the rest of the employees in that data set that was valid
