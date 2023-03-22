#Q1
drop function count_student;
DELIMITER //
create function count_student(fac_name varchar(45))
returns integer
DETERMINISTIC
Begin
declare s_count integer;
	select count(snum) into s_count from enrolled where cname in(select  name from faculty natural join class where faculty.fname=fac_name);
	return s_count;
end //
DELIMITER ;
    
    
select count_student('Ivana Teach') as 'number of student enrolled in given course';
select fname from faculty where count_student(fname)>1;
#Q2
drop function count_F_grade_student;
delimiter //
create function count_F_grade_student(course_name varchar(45))
returns integer
deterministic
begin
declare f_count integer;
	select count(snum) into f_count from enrolled2 where grade='F' and cname=course_name;
	return f_count;
end //
delimiter ;
select count_F_grade_student('Data structures') as 'number of student having F grade';
select fname from faculty natural join class where  count_F_grade_student(name)>1;

drop function ff1
 delimiter //
 create function f2(cour varchar(45))
returns integer
	deterministic
	begin
    declare c1 integer;
	select count(snum) into c1 from enrolled2 where grade='F' and cname=cour;
    return c1;
     end //
delimiter ;
select f2('data structures');
select fname from faculty natural join class where f2(name)>1;
