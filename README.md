CREATE TABLE crew_member (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20),
    role ENUM('MISSION_SPECIALIST','FLIGHT_ENGINEER','PILOT','COMMANDER'),
    `rank` ENUM('TRAINEE','SECOND_OFFICER','FIRST_OFFICER','CAPTAIN'),
    is_ready_for_next_mission BOOLEAN NOT NULL default TRUE
);

insert INTO crew_member(name, role, `rank`) VALUES
('Zoe Day',1,3);

CREATE TABLE spaceship (
    id INT NOT NULL PRIMARY KEY  AUTO_INCREMENT,
    name VARCHAR(20),
    flight_distance LONG NOT NULL,
    is_ready_for_next_mission BOOLEAN NOT NULL default TRUE
);
CREATE TABLE spaceship_crew (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    spaceship_id INT NOT NULL ,
    FOREIGN KEY (spaceship_id) REFERENCES spaceship(id),
    role ENUM('MISSION_SPECIALIST','FLIGHT_ENGINEER','PILOT','COMMANDER'),
    number_of_members INT NOT NULL
);

CREATE TABLE flight_mission (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    start_date_time DATETIME NOT NULL ,
    end_date_time DATETIME NOT NULL ,
    flight_distance LONG NOT NULL ,
    spaceship_id INT UNIQUE,
    FOREIGN KEY (spaceship_id) REFERENCES spaceship(id),
    mission_result ENUM('CANCELLED','FAILED','PLANNED','IN_PROGRESS','COMPLETED')
);

ALTER TABLE crew_member
ADD COLUMN flight_mission_id INT AFTER `rank`;

ALTER TABLE crew_member
ADD FOREIGN KEY (flight_mission_id) REFERENCES flight_mission(id);

ALTER TABLE spaceship
add column crew_id INT NOT NULL AFTER name;

ALTER TABLE spaceship
ADD FOREIGN KEY (crew_id) REFERENCES spaceship_crew(id);

INSERT INTO spaceship(name, flight_distance) VALUES
('Chalandger',201117),('Kokobab',30001),('Humanity',452982);

INSERT INTO spaceship_crew(spaceship_id, role, number_of_members) VALUES
(6,1,5),(6,2,9),(6,3,4),(6,4,1);

INSERT INTO spaceship_crew(spaceship_id, role, number_of_members) VALUES
(7,1,3),(7,2,5),(7,3,3),(7,4,1);

INSERT INTO spaceship_crew(spaceship_id, role, number_of_members) VALUES
(8,1,2),(8,2,6),(8,3,4),(8,4,2);

INSERT INTO crew_member(name, role, `rank`) VALUES
('Howdy Ho',2,4), ('Jina Tukl',1,2), ('Ed Stafford',4,4),  ('Michael Scott',1,1);

ALTER TABLE flight_mission
ADD name VARCHAR(20) AFTER id;

INSERT INTO flight_mission(name,start_date_time,end_date_time,flight_distance,
                           spaceship_id,mission_result)
VALUES ('Uganda','1000-01-01 00:00:00','2000-01-01 06:05:20',50000,6,3);

UPDATE crew_member
SET flight_mission_id = 1
WHERE id = 1;



################################################################
#JWD Final Java core task
##Requirements 
* Fork this [git repository](https://github.com/Rement/jwd-core-final)
* You should not remove MY comments
* You have to follow [Java code conventions](https://www.oracle.com/java/technologies/javase/codeconventions-contents.html) ! 
* Code must compile 
* You have to do the latest commit before **23:59 7th November (Minsk time)**
* Use slf4j for logging your actions (You should store INFO or higher messages in output log files, which have 5 generations)
* You are NOT able to use any codegenerators (i.e. Lombok)
* Console input should be done using java.util.Scanner
* Input files contains structure description, starts with hash

###Mandatory tasks: 
* In domain package update entity based on requirements
* Implements service interfaces
* Extend missed criteria implementations
* Update custom exception with meaningful messages (feel free to create your own exceptions, if you need them)
* Populate context with missing implementation
* Design UI for ApplicationMenu (user should be able to get/update information about CrewMembers, Spaceships. 
Able to create/update mission information). 
Able to write information about selected mission(s) in output file in json format



###Additional tasks:
* Create tests using Junit, Mockito for your functionality
* Implement additional option in a menu (for mission) with real-time flight-status
* Discuss with mentor any improvements, you want to implement 