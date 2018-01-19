# populate-restaurants

Pulling 120 records of restaurant data from four European cities (Roma, Milano, Paris, Brussels) and one Indian city (Mysore) and uploading to Recombee database.

Retrieving from Zomato API:
    
    Under -> /GET/search
    
    user-key = Zomato API-key
    entity_id = city_id in Zomato
    entity_type= city
    
    (e.g. GET --header "Accept: application/json" --header "user-key: <Zomato API-key>" "https://developers.zomato.com/api/v2.1/search?entity_id=<city_id>&entity_type=<city>")
    
    
    To get city_id in Zomato
    
    Under -> /GET/cities
         
    user-key= Zomato API-key
    q = city name (e.g. Rome)
    
    (e.g. GET --header "Accept: application/json" --header "user-key: <Zomato API-key>" "https://developers.zomato.com/api/v2.1/cities?q=<city name>")

Saving to "restaurants.csv" and after manually formatting data populating Recombee items table with the following properties:

- id (string)
- type (string)
- name (string)
- city (string)
- topic (string)
- address (string)
- rating (string)
    
    *address and rating are new property items added to the Recombee database. Other property items are not added again as they were already created as part of activities data upload to Recombee database. 
    *From and to property items which were created for activities data are automatically set to null for all the restaurants data that has been uploaded to Recombee database.

Reference:

- Recombee API (version 1.6.0), Available at: https://docs.recombee.com/api.html.
- Zomato API, Available at: https://developers.zomato.com/api#headline1.
