# 21st Street Labor Chart Client
> Client application uses the Discord API to handle commands sent by members relating to 
updating and accessing the 21st Street Labor Chart. This application updates the database by consuming the REST API endpoints created in the separate server program.
The application also contiously notifies laborers when their shifts are starting through messages in our facility's Discord server.

Server can be found here: <https://github.com/AshfordHastings/Labor-Chart-Server>

> Note: The project is in progress and has been stepped away from briefly. The next steps involve building front-end of the application, as opposed to fixing the bugs in the Discord communication tool. Updates will be posted soon! This is a highly anticipated project by our housing cooperative, and we are working on creating a solid timeline for the deployment of this application.

## To-Do:
- [X] Complete methods meant to consume the server's API. 
- [ ] Update service for notifying members at labor times
- [ ] Parse Google Sheet for labor positions as opposed to relying purely on Discord commands

## Motivation:
Oftentimes, members participating in our labor system can forget to complete their weekly labor assignments due to their busy student schedules. This can often come at 
detriment to our house, as members rely on labor attendence for the house to run smoothly. Seeing this issue gave me the idea to develop an application that could
parse the Google Sheet containing our labor assignments and send hourly notifications to members at their labor times. Notifications are made through our house communications channel 
hosted on Discord, so other members can also see who has labor at what time. 

## Future Goals:
Our future goals are to expand this program into a full-stack web application, where the Labor Coordiator can login with a unique registration and update members' labor assignments online.
Members would still be notified through the facility's Discord channel, but the actual information could be updated online. Beyond that, we want house members to be able to 
use the website to list their weekly avaliability through the website. Then, when the Labor Coordinator is assigning labor through the application, slots where members have stated their 
unavaliability could be greyed out, making coordinating the labor schedule significantly easier. Another future goal would be to implement an SMS delivery service, such as Twilo, to
notify members at their labor times, as opposed to using strictly the Discord API.

## About Us:

21st Street Co-op is a one-hundred member student housing cooperative managed by [College Houses](https://collegehouses.org/), a registered 501c3 nonprofit that provides affordable student housing near
UT Austin and Austin Community College. Established in August 1974 and located at 707 W 21st Street, 21st Street Co-op strives to provide a unique, affordable, democratic, and community-driven 
living-space to its members.

Our co-op thrives on a communal labor system in which
each member contributes four hours of labor per week, as coordinated by our elected Labor Officer. Labor includes meal preparation, cleaning, groundwork, and maintenence. This member-led system
allows for a reduction of rent. Weekly meetings are held where decisions regarding the budget, house policy, and
membership concerns are made through a democratic parliamentary procedure. This system of autonomy and self-governence gives members' valuable experience in how to live and 
interact with a community. 
