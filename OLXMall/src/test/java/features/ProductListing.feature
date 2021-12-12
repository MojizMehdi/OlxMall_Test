Feature: ProductListing

  Scenario: User should be able to filter Product Listings by Price
    Given the user navigate to olx web page "olx_url"
    When I am hovering my mouse to "home_allCategories"
    And I am hovering my mouse to "home_mobileTablet"
    And I am clicking on "home_MobileOption"
    When I have given "40000" on "filter_minPrice"
    And I have given "120000" on "filter_maxPrice"
    Then I am verifying first ten items on screen