Feature: Order the phone number with mobile device
  User place an order on orange website

  Scenario: Order with incorrect personal data

  Given User navigates to Orange website
  And User clicks on the mobiles offers
  And User clicks on first offer with subscription' details button
  And User matches resign from additions option
  When User adds offer to basked
  And User confirms the order
  And User enters incorrect personal details
  And User approves all conditions
  And User submits the order
  Then User is informed that order can not be realized
  And User cancels the order
  And User is informed that my basket is empty

