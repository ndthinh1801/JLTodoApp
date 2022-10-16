# To-do App for Interview

The purpose of this coding challenge is to see how you build the architecture of an app from
scratch. Please try using Object-Oriented style/Interface based programming with clean
architecture and a generic approach in mind to extend the app in the future. You don’t need to
write the test cases, but the code should have the ability to test through Unit test cases.
We are not interested in UI but want to see how you bind the UI with data. Please use any
architectural or design patterns when you think you should use them.

Time spent: 2 days spent in total
# User Stories
- The demo app has three types of data to be shown in separate listings on different screens.
For demo purposes, data on two screens will be fetched from web API endpoints, and on one
screen, you will fetch data from a local store.

1. Home Screen: This screen will have three buttons, To Call, To Buy, and To Sell, which will
navigate the user to its listing screens.

<img width="636" alt="image" src="https://user-images.githubusercontent.com/19640672/194726602-8a5ae72d-313e-47c3-95eb-908b8d1aeb89.png">

2. To-Call Listing Screen: It will show a list of persons to call. The data is returning from
a web API call in JSON format.
https://my-json-server.typicode.com/imkhan334/demo-1/call

3. To-Buy Listing Screen: It will show a list of items to buy (type 1). The data is returning
from a web API call in JSON format.
https://my-json-server.typicode.com/imkhan334/demo-1/buy

4. To-Sell Listing Screen: It will show a list of items to sell (type 2). This data should be
stored in an SQLite database table named “ItemToSell”.
  
<img width="335" alt="image" src="https://user-images.githubusercontent.com/19640672/194726698-bf41060a-6372-4b9b-9da0-42ff61d15d86.png">
