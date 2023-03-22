drop trigger set_credits;
delimiter //
create trigger set_credits after update on enrolled2
for each row
begin
declare credit_count integer default 0;
if 
(new.grade<>'F' and new.grade is not null
and (old.grade='F' or old.grade is null ))
then
update student set total_credits=(select sum(credits) from class where class.name=new.cname) where student.snum=new.snum;
else if
(old.grade<>'F' and new.grade='F')
then
update student set total_credits=0 where student.snum=new.snum;
end if;
end if;
end //
delimiter ;

update enrolled2 set grade='A' where snum=348121549;
update enrolled2 set grade='A' where snum=115987938;

#Q2
drop trigger add_remove_student;
delimiter //
create trigger add_remove_student after update on student
for each row
begin
if
new.total_credits>10
then
insert into ready_student values(new.snum) ;
else if
new.total_credits<=10
then
delete from ready_student where snum=new.snum;
end if;
end if;
end //

delimiter ;

