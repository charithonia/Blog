Hello!
This is a Java web application that is an approximation of a blog.

It relies on a database (one for the application and an additional one for its tests) to function; I have
personally used both MySQL and mariaDB successfully. Scripts for creating the necessary databases (one for
the project's tests) are available in this folder. These scripts may not be perfect! The final scripts we
were using were lost in a Git mix-up, but these backups will at least allow the project to run and provide
much of its data.

This application runs successfully for me using Apache Tomcat (I've used 8 and 9 successfully). The
application works best in Chrome; some elements do not seem to appear or function correctly in Firefox.

Once running, one can log in. The blog is designed to allow only admins and approved users to create content.
Username:
admin
Password:
admin

Due to some last-minute experimentation we were doing, the front-end is a bit scrambled and the ability to
create new blogs seems to have become damaged. However, one can successfully navigate the site and use
some of the features to see how they affect changes on the site, and the creation pages for blog content
demonstrate the content management features it was intended to (and did) provide.

The most important aspect of the code is the MVC design of the project as a whole. I am happy to answer
any questions about the project, why certain decisions were made, what I would fix in hindsight, and what I
still plan to fix or change now that I've had some time to review it again. If you're reading this (and I
hope that you are!), I'm sure you already know how to get in touch!