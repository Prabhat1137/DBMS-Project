#1
SELECT level,avg(age) FROM university.student group by level;
#2
SELECT level,avg(age) FROM university.student where level<>'JR' group by level; 
#3
select fname,count(name) as no_of_course from faculty,class where faculty.fid=class.fid group by fname;
#4
select sname from enrolled natural join student where cname='Database Systems' except(select sname from enrolled natural join student where cname='Operating System Design');
#5
select cname,count(sname),avg(age)  from student natural join enrolled group by cname having count(sname)>=2;
select cname,count(sname),avg(age)  from student,enrolled where student.snum=enrolled.snum group by cname having count(sname)>=2;
#6
select fname,fid,count(name)  from faculty natural join class group by fid having count(name)>=2;
select fname,faculty.fid,count(name)  from faculty,class where faculty.fid=class.fid group by fid having count(name)>=2;
#7
select sname,student.snum,count(cname)  from student natural join enrolled group by cname having count(cname)>=2;
select sname,student.snum,count(cname)  from student,enrolled where student.snum=enrolled.snum group by cname having count(cname)>=2;
#8
select *from student order by age asc;
#9
 select snum,sname,major from student where major='Electrical Engineering' or major='Computer Engineering' or major='Mechanical Engineering' or major='Civil Engineering';
#10
 select major,count(sname) from student where major in ('Computer Engineering','Electrical Engineering','Civil Engineering','Mechanical Engineering') group by major;
 
 