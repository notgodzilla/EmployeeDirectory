## Build tools & versions used
Build Tools 34.0.0
Compile SDK 33
Moshi 1.8.0
Coil 2.0.0

## Steps to run the app
Build apk then run on device. App will pull from the 'valid' employee endpoint by default. The pull to refresh functionality also will only pull from the 'valid' employee endpoint. THe 'malformed' and 'empty' employee endpoints can be selected from the drop down menu on the top right corner.

## What areas of the app did you focus on?
I focused primarily on getting the basic functionality to work as described: being able to fetch the employee list from the three given endpoints. I also implemented the logic in a Fragment so it would be easier to add more functionality to the app such as navigating to another Fragment for more employee details, etc. 

## What was the reason for your focus? What problems were you trying to solve?
I primarily focused on getting the minimum viable product to functional so there could be room to add more functionality later. Much of my focus was done on the networking portion of the app as I had initially intended to handle the endpoint with the malformed employee by excluding that malformed data point and displaying the rest of the employees there were valid in that data set. I spent a lot of time trying to figure out how to do this but decided to just default to displaying a generic error and treating the malformed data set as an empty list.  

## How long did you spend on this project?
6hrs

## Did you make any trade-offs for this project? What would you have done differently with more time?
I did not have time to implement unit tests as it was something I have not had experience with implementing from scratch. I had saved it for last with the intention of spending some time learning how to do this on my own but did not want to spend more time on the project as recommended. As mentioned above I spent most of my time making sure the networking portion of the app was working as needed and working on the very basic functionality of the app (loading all the given endpoints, handling errors, and refreshing)

## What do you think is the weakest part of your project?
I feel like that weakest parts are the architecture and legibility of the code. There is a lot of boilerplate code in the EmployeeDirectoryFragment and some repetitiveness that I initially wanted to factor out and clean up a bit. I also feel like I could have structured the project much better by factoring out the logic and making smaller more modular classes so the classes themselves were easier to read.

## Did you copy any code or dependencies? Please make sure to attribute them here!
I heavily referenced a previous project of mine (simple Flickr app) that I created by studying Android Programming: The Big Nerd Ranch Guide (5th Edition)
Chapter referenced: Chapter 20 Making Network Requests and Displaying Images
This chapter uses the libraries Moshi and Coil for networking and displaying images respectively. Coil by default automatically caches images in disk, which was one of the project requirements and is why I decided to use Coil. 

## Is there any other information you’d like us to know?
I learned a lot while working on this project and enjoyed working on it!

## NOTES & KNOWN MINOR BUGS
There were a couple things I wanted to change / implement but did not have the time to such as
- Unit Tests
- Factor out use of deprecated use of options menu in EmployeeDirectoryFragment
- Handle endpoint with malformed dataset by excluding that employee and displaying the rest of the employees in that data set that was valid