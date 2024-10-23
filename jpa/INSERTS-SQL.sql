insert into tb_publisher values(gen_random_uuid(), 'Alta Books');
insert into tb_publisher values(gen_random_uuid(), 'Pearson');



insert into tb_author values(gen_random_uuid(), 'Eric Evans');
insert into tb_author values(gen_random_uuid(), 'Paul Deitel');
insert into tb_author values(gen_random_uuid(), 'Hervey Deitel');


select * from tb_book;
select * from tb_publisher;
select * from tb_author;
select * from tb_book_author;
select * from tb_review;
