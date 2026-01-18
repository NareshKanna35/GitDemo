@tag
Feature: Error validations


	@errorcheckcases
	Scenario Outline: Negative test of purchase order
	Given  i landed on the ecom page
	When  Log in with username <name> and password <pswd>
	Then "Incorrect email or password." message is displayed
	
	
	Examples:
	|name|pswd|
	|nareshkannapgn@gmail.com|Test@@123|

