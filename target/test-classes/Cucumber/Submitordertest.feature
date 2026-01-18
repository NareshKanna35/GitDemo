@tag
Feature: Purchase order test

Background:
Given  i landed on the ecom page


	@functional
	Scenario Outline: Postive test of purchase order
	Given Log in with username <name> and password <pswd>
	When I add the products <products> to cart
	And checkout <products> and submit the order
	Then "THANKYOU FOR THE ORDER." is displayed in the confirmation page
	
	
	Examples:
	|name|pswd|products|
	|nareshkannapgn@gmail.com|Test@123|IPHONE 13 PRO,ZARA COAT 3|

