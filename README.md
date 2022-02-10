# Schedule Assistant

This is a GUI-based application created using Java, Java FX, and MySQL. It is an appointment and customer management system that allows the user to add, modify, or delete appointments and customers. The application connects to a local MySQL database where the data is stored. Local Date/Time functionality is included, as all time is displayed to the user in their local military time. Time is converted and stored in the database in UTC. The user must input valid credentials to access the system, and it will alert the user if there is an upcoming appointment within 15 minutes of the login time. Appointments can be filtered by week, date, or month. Specific reports can be generated displaying the number of appointments by month and type, appointments by the contact, and customers by their country location. Overlapping appointments and appointments outside of business hours (set from 8:00 a.m. to 10:00 p.m. EST) are not allowed. A text file is also generated tracking login activity. The Model View Controller (MVC) design pattern was utilized to develop the application.

# Demo

This is the login screen. The user's Zone ID is displayed in the top right.

![image](https://user-images.githubusercontent.com/77899871/153324830-a54b4db1-9145-441c-8486-ee4becad2e5b.png)

An error will prompt if invalid credentials are entered.

![image](https://user-images.githubusercontent.com/77899871/153325263-f1772e26-643f-4bbc-87a1-91459b0ac00c.png)

If the login is successful, an alert will prompt advising the user if there is an upcoming appointment within 15 minutes.

![image](https://user-images.githubusercontent.com/77899871/153332954-8eab997b-de7f-4ad8-ad0b-dc25d49268e2.png)
![image](https://user-images.githubusercontent.com/77899871/153332977-9644d261-fa47-4a69-aa4d-6920f306de6a.png)

This is the main screen. The appointment view is displayed by default, and the user can click the customers tab to view customers. The user can also filter appointments by month or week. 

![image](https://user-images.githubusercontent.com/77899871/153333234-47b96973-e824-42bf-a50d-6f3aa7234712.png)

The customer view.

![image](https://user-images.githubusercontent.com/77899871/153333263-0aaaf90f-86da-4243-ba7b-b397c037a1bc.png)

The add appointment screen:

![image](https://user-images.githubusercontent.com/77899871/153333321-cab51f93-a407-44b0-bffb-143b8138fbb1.png)

The modify appointment screen:

![image](https://user-images.githubusercontent.com/77899871/153333363-897f1d30-4491-438a-a38e-05ad372026bb.png)

Modifying or adding an appointment outside of business hours will prompt an error:

![image](https://user-images.githubusercontent.com/77899871/153333467-565e72e2-464e-4880-8bd5-822711e3e87a.png)

They will also prompt an error if the appointment overlaps with another:

![image](https://user-images.githubusercontent.com/77899871/153335473-8e4fcb9e-82e6-49f6-9a3d-5841b9b278f8.png)![Uploading image.png…]()

The add customer screen:

![image](https://user-images.githubusercontent.com/77899871/153333398-a5385e85-2771-4014-8028-de3ca35c1d13.png)

Both customers and appointments can be deleted. The user will be prompted to confirm the deletion, along with any cancellations or saves. If an apppointment deletion is confirmed, an alert will provide the deleted appointment information:

![image](https://user-images.githubusercontent.com/77899871/153333839-5be8eeb1-5a30-45c1-8b83-0311c08b8479.png)
![image](https://user-images.githubusercontent.com/77899871/153333933-da8a1a87-8fc6-48ed-9123-72ff0fdefac4.png)

Selecting the reports button from the main screen will bring the user to the first report. It displays the number of appointments by appointment type and month:

![image](https://user-images.githubusercontent.com/77899871/153334477-80720e8b-2ffd-4237-8c33-29b301e995bd.png)

Appointment schedule by contact and customers by country location can also be viewed:

![image](https://user-images.githubusercontent.com/77899871/153334733-5a49c498-cf48-480e-85d1-38de1dce2750.png)
![image](https://user-images.githubusercontent.com/77899871/153334765-814f2b97-e16e-4681-9161-f68ecc8d3ab8.png)

# Installation
- Due to the application utilizing a local MySQL database to access and store data, installation is not available at this time.


