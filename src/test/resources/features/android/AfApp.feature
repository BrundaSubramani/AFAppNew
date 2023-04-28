Feature: Agent facing E2E APP new journey flow

  Scenario:To verify Sales rep is able to perform an E2E
    Given Sales representative lands on salesforce page
    #When Sales representative filled up UserName "<username>" and password "<Password>" to login
    #And  Sales representative searches respective DMU profile in Global search
    And  Sales representative logs in to the DMU profile
    And  Sales representative creates a new lead in object lead
    And  Sales representative converts the lead into account by clicking on convert
    And  Sales representative enter the account name subject and clicks on convert
    And  Sales representative proceeds to place and order by clicking order button
    And  Sales representative lands on business shop to select app purchases
    And  Sales representative lands application page and chooses apps available
    And  Sales representative performs purchased app configurations and proceeds to place an order
    And  Sales representative enters the business details on transactional checkout page
    And  Sales representative enters the personal details on transactional checkout page
    And  Sales representative enters the delivery details on transactional checkout page
    And  Sales representative enters the monthly payment details on transactional checkout page
    And  Sales representative enters the upfront payment details on transactional checkout page
    #Then Sales representative confirms the purchase order is generated
