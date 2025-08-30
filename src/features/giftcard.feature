Feature:

Background:
Given user is on the BookMyShow home page and enter the city

Scenario:
Validate Gift Card and error message for inavalid voucher

Given user click the Gift Card button
 Then user see the "Check Gift Card Balance" icon
 When user click the "Check Gift Card Balance" icon
 And user enters an invalid voucher code "INVALID123"
 And user click the Check balance
 Then user see the error message "Invalid Gift voucher Code. (#-4426)"