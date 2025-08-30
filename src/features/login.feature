Feature:
Login functionality of BookMyShow

Scenario:
Successfully login with valid credentials

 Given user is on login page
 When I select my city
 And  I logs in with mobile and otp
 Then login should be successful
 
Scenario:
Invalid login attempt

 Given user is on login page
 When I am login with wrong mobile and otp
 Then login should be failed
 
 
Scenario:
Check UI Elements
 Given user is on login page
 Then all login UI elements should be visible

