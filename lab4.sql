#Q1
 select  snum,sname,min(age) from enrolled natural join student where major='Finance' or cname in(select  name from class,faculty where fname='Linda Davis' and class.fid=faculty.fid);
 #Q2
  select  fid,fname from faculty f where not exists((select room from class c) except (select c1.room from class c1 where c1.fid=f.fid));
  select fid,fname from faulty where not exists((select fid from class,faculty group by room) except(select distinct fid from faculty)));
  #Q3
  select f.fname from faculty f where f.fid in (select fid from class group by fid having count(*) <= all(select count(*) fid from class group by fid));
  #Q4
  select fname from faculty except select fname from faculty natural join class;
  #Q5
  select s.level,s.age from student s group by s.age,s.level having s.level in (select s1.level from student s1 where s1.age = s.age group by s1.level,s1.age having count(*) >= all(select count(*) from student s2 where s1.age = s2.age group by s2.level,s2.age));
  #Q6
  select cl.name from class cl where cl.room="R128" and cl.name in (select cname from class,enrolled where class.name = enrolled.cname group by name having count(*) >= 1);
  #Q7
  select cl.name,cl.meets_at from class cl where cl.name in (select cname from class,enrolled where class.name = enrolled.cname group by name having count(*) >= 1);
  #Q8
  select s.sname from student s where s.level="JR" and s.snum in(select snum from enrolled,class where enrolled.cname = class.name and room="R128");
  #Q9
  select sname from student where age>18 and level="SR" and not major like "%Engineering";
  #Q10
  select name from class except select cname from enrolled;
  select level,age from
  