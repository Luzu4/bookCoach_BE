insert into book_coach.games (name, description, image_url, short_game_name, deleted) VALUES
                                                                                          ('Counter-Strike 2','Counter-Strike 2 is the largest technical leap forward in Counter-Strike’s history, ensuring new features and updates for years to come','https://cdn.cloudflare.steamstatic.com/apps/csgo/images/csgo_react/social/cs2.jpg','CS 2',false),
                                                                                          ('Diablo 4','Diablo IV is an upcoming action role-playing game developed and published by Blizzard Entertainment, and is the fourth main installment in the Diablo series.','https://upload.wikimedia.org/wikipedia/en/thumb/1/1c/Diablo_IV_cover_art.png/220px-Diablo_IV_cover_art.png','Diablo 4',false),
                                                                                          ('Valorant','Valorant is a team-based first-person tactical hero shooter set in the near future. Players play as one of a set of Agents, characters based on several countries and cultures around the world.','https://i0.wp.com/playerassist.com/wp-content/uploads/2020/11/valorant-wiki-guide.jpg?fit=762%2C397&ssl=1','Valo',false),
                                                                                          ('FIFA 2024','FIFA 23 is a football video game published by Electronic Arts. It is the 30th and final installment in the FIFA series that is developed by EA Sports','https://image.api.playstation.com/vulcan/ap/rnd/202301/0312/uj4557qP6A0DLJdillyqVHMM.png','Fifka',false);

insert into book_coach.user_details_all (city, country, language, description, image_url)
values
    ('Sierpc','Poland','Polish','Hey whats up?', 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8cmFuZG9tJTIwcGVvcGxlfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60'),
    ('Gdansk','Poland','English','Hey whats up2?', 'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80'),
    ('Warsaw','France','French','Some sick description','https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80'),
    ('Warsaw','France','French','What you think?','https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80'),
    ('Katowice','Netherlands','Spanish','Dragon Ball Fan Auuuu','https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80'),
    ('Sierpc','Poland','Polish','Beast from east ','https://images.unsplash.com/photo-1525134479668-1bee5c7c6845?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80'),
    ('Gdansk','Poland','English','Description of good man','https://images.unsplash.com/photo-1530268729831-4b0b9e170218?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80'),
    ('Warsaw','France','French','Latala mucha i spadla do wody','https://images.unsplash.com/photo-1504199367641-aba8151af406?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8M3x8fGVufDB8fHx8&auto=format&fit=crop&w=500&q=60'),
    ('Warsaw','France','French','Wrotki bez wrotek','https://images.unsplash.com/photo-1499155286265-79a9dc9c6380?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=684&q=80'),
    ('Katowice','Netherlands','Spanish','Bum Bum Bum Bum','https://images.unsplash.com/photo-1531384441138-2736e62e0919?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80');



insert into book_coach._user (email, is_verified, nick_name, password, role, user_details_all_id)
VALUES
    ('admin@gmail.com',false,'Admin','$2a$10$6bPYChA7nS9KUMdCgCAOSOpDP/5O1c7DvLTyVlYgcgNKgsEzLkCCO','ADMIN',1),
    ('coach@gmail.com',false,'Coach','$2a$10$6bPYChA7nS9KUMdCgCAOSOpDP/5O1c7DvLTyVlYgcgNKgsEzLkCCO','COACH',2),
    ('player@gmail.com',false,'Player','$2a$10$6bPYChA7nS9KUMdCgCAOSOpDP/5O1c7DvLTyVlYgcgNKgsEzLkCCO','PLAYER',3),
    ('adam@gmail.com',false,'AdamCoach','$2a$10$6bPYChA7nS9KUMdCgCAOSOpDP/5O1c7DvLTyVlYgcgNKgsEzLkCCO','COACH',4),
    ('maciek@gmail.com',false,'MaciekCoach','$2a$10$6bPYChA7nS9KUMdCgCAOSOpDP/5O1c7DvLTyVlYgcgNKgsEzLkCCO','COACH',5),
    ('johny@gmail.com',false,'JohnyPlayer','$2a$10$6bPYChA7nS9KUMdCgCAOSOpDP/5O1c7DvLTyVlYgcgNKgsEzLkCCO','PLAYER',6),
    ('ananas@gmail.com',false,'AnanasCoach','$2a$10$6bPYChA7nS9KUMdCgCAOSOpDP/5O1c7DvLTyVlYgcgNKgsEzLkCCO','COACH',7),
    ('pilka@gmail.com',false,'PlikaCoach','$2a$10$6bPYChA7nS9KUMdCgCAOSOpDP/5O1c7DvLTyVlYgcgNKgsEzLkCCO','COACH',8),
    ('janusz@gmail.com',false,'JanusCoach','$2a$10$6bPYChA7nS9KUMdCgCAOSOpDP/5O1c7DvLTyVlYgcgNKgsEzLkCCO','COACH',9),
    ('stanislaw@gmail.com',false,'StanislawCoach','$2a$10$6bPYChA7nS9KUMdCgCAOSOpDP/5O1c7DvLTyVlYgcgNKgsEzLkCCO','COACH',10);


insert into book_coach.user_details_all_game (user_details_all_id, game_id)
VALUES
    (1,1),(1,2),(1,3),(2,2),(2,3),(2,4),(4,3),(4,4),(4,1),(5,4),(5,1),(5,2),(7,1),
    (7,2),(7,3),(8,2),(8,3),(8,4),(9,3),(9,4),(9,1),(10,4),(10,1),(10,2);

insert into book_coach.lessons (date, time, game_id, user_id, deleted)
VALUES ('2024-04-18', '15:00', 1,1,false),
       ('2024-04-17', '16:00', 2,1,false),
       ('2024-04-18', '17:00', 3,1,false),
       ('2024-04-17', '18:00', 2,2,false),
       ('2024-04-18', '08:00', 3,2,false),
       ('2024-04-18', '09:00', 4,2,false),
       ('2024-04-18', '10:00', 3,4,false),
       ('2024-04-18', '11:00', 4,4,false),
       ('2024-04-18', '12:00', 1,4,false),
       ('2024-04-18', '20:00', 4,5,false),
       ('2024-04-18', '10:00', 1,5,false),
       ('2024-04-18', '11:00', 2,5,false),
       ('2024-04-18', '12:00', 1,7,false),
       ('2024-04-18', '20:00', 2,7,false),
       ('2024-04-18', '20:00', 3,7,false),
       ('2024-04-18', '08:00', 2,8,false),
       ('2024-04-18', '09:00', 3,8,false),
       ('2024-04-18', '10:00', 4,8,false),
       ('2024-04-18', '11:00', 3,9,false),
       ('2024-04-18', '12:00', 4,9,false),
       ('2024-04-18', '20:00', 1,9,false),
       ('2024-04-18', '10:00', 4,10,false),
       ('2024-04-18', '11:00', 3,10,false),
       ('2024-04-18', '12:00', 4,10,false);

insert into book_coach.happy_students (description, image_url, name) VALUES
                                                                         (' - lat 30 Thank god that I found this site','https://images.unsplash.com/photo-1528892952291-009c663ce843?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MTR8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60','Maciej'),
                                                                         (' - Nice place to learn how to play games', 'https://images.unsplash.com/photo-1469334031218-e382a71b716b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MTd8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60','Weronika'),
                                                                         (' - It was pleasure to work with Luz', 'https://images.unsplash.com/photo-1531746020798-e6953c6e8e04?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MTl8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60', 'Joanna'),
                                                                         (' - ALOOOO AUUUU IM BEAST NOW','https://images.unsplash.com/photo-1519764622345-23439dd774f7?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MTZ8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60','Sebastian'),
                                                                         (' - Who wants 1vs1? ','https://media.istockphoto.com/id/1370511233/photo/young-woman-working-from-home-with-a-boston-terrier-dog-freelancer-businesswoman-using-laptop.jpg?b=1&s=170667a&w=0&k=20&c=gBZp146qhWPdsfKExEZnM3ZuLXDjbPSBwyVRA5OQZT4=','Sylwia');
