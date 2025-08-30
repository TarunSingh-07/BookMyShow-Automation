Feature:
City Search Module

Scenario:
Search for a valid city

 Given user is on the city selection page
 When user enters a valid city name "Delhi"
 Then the correct city "Delhi" should be displayed
 
Scenario:
Search for an invalid city

 Given user is on the city selection page
 When user enter an invalid city name "aabbedf"
 Then an error message "No results found." shoulld be displayed

Scenario:
Select city by clicking on popular city icon

 Given user is on the city selection page
 When user clicks on the city icon "Hyderabad"
 Then the correct city "Hyderabad" should be selected
 
Scenario:
View all cities and validate other cities

 Given user is on the city selection page
 When user clicks on city dropdown and selects "View All Cities"
 Then I should see the cities "<city>" in All Cities
    Examples:
    | city   |
    | Patna  |
    | Kanpur |