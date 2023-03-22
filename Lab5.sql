#Q1
select name from class where room='20 AVW' or name in(select cname from enrolled  group by cname having count(*)>=5);
#Q2
 select room,count(name) from class group by room;
#Q3
select faculty.fid,fname,count(name) from faculty left OUTER JOIN class on faculty.fid=class.fid group by fid;
#Q4
select name,room,count(*) as number_of_students from class,enrolled where class.name = enrolled.cname group by name,room UNION select name,room,0 as number_of_students from class where name not in (select cname from enrolled);
  
#Q5
select faculty.fid,fname,deptid,room from faculty natural join class where faculty.deptid='20' and class.room='R128';
#Q6
select snum,sname,major,max(age) from student group by major;
#Q7
select sname as names from student where sname like "%son%" union select fname from faculty where fname like "%son%";
#Q8
select supervisor from empl_super where person='Ravi';
#Q9
select supervisor from empl_super where person=(select supervisor from empl_super where person='Ravi');
#Q10
 with recursive deps as (select person,supervisor from empl_super where person = "Ravi" union all select p.person,p.supervisor from empl_super p join deps d on d.supervisor = p.person) select supervisor from deps;
 
    
  