Feature:
Movie Module Functionality

Background:
Given user is on the city selection page and choose the city "Delhi-NCR"

Scenario:
Verify recommended movie navigation

  Given user notes the first recommended movie title
  When user clicks on the first recommended movie card
  Then the movie title on details page should match the noted title

Scenario:
 Validate UI components on Movie screen
 
  Then movies tab should be visible
  And movie search box should be visible
  
Scenario:
Verify upcoming movies navigation

 When user clicks on the Movies tab in the Home screen
 And user clicks on "Explore Upcoming Movies" section
 Then "In Cinemas Near You" section should visible