insert into users(id, username, password)
values (1, 'bruce', 'wayne'),
       (2, 'peter', 'security_rules'),
       (3, 'tom', 'guessmeifyoucan'),
        (4, 'santa', 'clause');

insert into persons(id, firstName, lastName, email)
values (1, 'bruce', 'wayne', 'notBatman@gmail.com'),
       (2, 'Peter', 'Petigrew', 'oneFingernailFewerToClean@gmail.com'),
       (3, 'Tom', 'Riddle', 'theyGotMyNose@gmail.com'),
        (4, 'Santa', 'Clause', 'st@northPole.com');

insert into gift(id, name, description, price)
values (1, 'Christmas tree decoration', 'Decoration for a Christmas tree', 500.01),
       (2, 'Christmas tree', 'A fake christmas tree', 1500.99),
       (3, 'Santa Clause', 'A small Santa Clause for decoration', 750.00),
       (4, 'Sweater', 'New Year sweater', 3000.00);

insert into tags(id, name)
values (1, 'small'),
       (2, 'big'),
       (3, 'wholesome'),
       (4, 'clothing'),
       (5, 'toy'),
       (6, 'doll'),
       (7, 'car');

insert into gift_to_tag(giftId, tagId)
values (1, 1),
       (1, 3),
       (2, 2),
       (2, 3),
       (3, 1),
       (3, 3),
       (4, 4);

insert into ratings(giftId, userId, rating)
values (1, 3, 5),
        (3, 2, 1),
        (3, 1, 3),
        (1, 1, 5),
        (1, 2, 4);

insert into comments(giftId, userId, comment)
values (1, 1, 'Very nice.');

insert into roles(id, name)
values (1, 'ADMIN'),
       (2, 'MANAGER');

insert into user_to_roles(userId, roleId)
values (4, 1),
       (3, 2);

