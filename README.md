# Ticket-Service
Implemented a simple ticket service that facilitates the discovery, temporary hold, and final reservation of seats within a high-demand performance venue.

# Maven Commands:
Assuming current directory is at this repository (where pom.xml is present).
1. mvn clean package assembly:assembly -DskipTests (To skip the test cases)
2. java -jar target\Ticket-Service-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.walmart.ticket.Application


# Service Implementation:
* TicketServiceImpl: Implement all the required functionalities that also includes booking of best available seats. ExpiringMap is used here to store Seat Hold objects and to delete them automatically after set expiry period.

# Implementation:
Data models that are used:
* SeatPosition: It identify seat position in matrix that has rows named with letters and seats per row in numbers. 
* Seat: It contains seatPosition, reservedBy (Customer), seatStatus (using enum).
* SeatHold: It has list of seats hold by customer including booking and validity period of hold seats.
* Venue: It is the theatre that has seats arranged in matrix format.
* Customer: It contains email information of customer.

# Test Implementation: 
* Build test cases to test the basic functionalities of ticket-service.
