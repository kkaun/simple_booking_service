# Simple Booking Service

This is a demo project, implementing basic functions of booking service, such as:
  - aggregating available placement options based on load factor of hotels(from this point on: objects);
  - booking of chosen apartment(s);
  - voting for featured objects;
  - managing user bookings;
  - managing own objects as hotel manager authority holder;
  - administrating the whole service as system admin role holder;
  - moderation as system admin role holder;  

Project is localized in English & Russian.

At the moment CRUD/Admin/Manager parts are made as REST/ajax services. Business(*selling*) part 
is less complex and made upon simple Spring MVC Controller processing, so I'm planning to transfer 
this part on MVC/AngularJs tandem in nearest future.

Current project stack: Maven/Spring(MVC, DataJPA, Security).
