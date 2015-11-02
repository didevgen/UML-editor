/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     02.11.2015 12:26:08                          */
/*==============================================================*/


drop table if exists Collaborator;

drop table if exists Diagram;

drop table if exists Diagram_Status;

drop table if exists Event;

drop table if exists EventType;

drop table if exists History;

drop table if exists User;

/*==============================================================*/
/* Table: Collaborator                                          */
/*==============================================================*/
create table Collaborator
(
   user_id              bigint,
   diagram_id           bigint
);

/*==============================================================*/
/* Table: Diagram                                               */
/*==============================================================*/
create table Diagram
(
   diagram_id           bigint not null auto_increment,
   owner_id             bigint,
   status_id            bigint,
   json_data            varchar(MAX) not null,
   created_date         datetime not null,
   last_updated         datetime not null,
   primary key (diagram_id)
);

/*==============================================================*/
/* Table: Diagram_Status                                        */
/*==============================================================*/
create table Diagram_Status
(
   status_id            bigint not null auto_increment,
   status_type          varchar(256) not null,
   primary key (status_id)
);

/*==============================================================*/
/* Table: Event                                                 */
/*==============================================================*/
create table Event
(
   event_id             bigint not null auto_increment,
   event_type_id        bigint,
   timestamp            datetime not null,
   primary key (event_id)
);

/*==============================================================*/
/* Table: EventType                                             */
/*==============================================================*/
create table EventType
(
   event_type_id        bigint not null auto_increment,
   description          varchar(256) not null,
   primary key (event_type_id)
);

/*==============================================================*/
/* Table: History                                               */
/*==============================================================*/
create table History
(
   event_id             bigint,
   user_id              bigint
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   user_id              bigint not null auto_increment,
   full_name            varchar(256) not null,
   email                varchar(256) not null,
   password             varchar(MAX) not null,
   registration_date    datetime not null,
   last_available       datetime not null,
   primary key (user_id)
);

alter table Collaborator add constraint FK_Reference_2 foreign key (user_id)
      references User (user_id) on delete restrict on update restrict;

alter table Collaborator add constraint FK_Reference_3 foreign key (diagram_id)
      references Diagram (diagram_id) on delete restrict on update restrict;

alter table Diagram add constraint FK_Reference_1 foreign key (owner_id)
      references User (user_id) on delete restrict on update restrict;

alter table Diagram add constraint FK_Reference_4 foreign key (status_id)
      references Diagram_Status (status_id) on delete restrict on update restrict;

alter table Event add constraint FK_Reference_5 foreign key (event_type_id)
      references EventType (event_type_id) on delete restrict on update restrict;

alter table History add constraint FK_Reference_6 foreign key (event_id)
      references Event (event_id) on delete restrict on update restrict;

alter table History add constraint FK_Reference_7 foreign key (user_id)
      references User (user_id) on delete restrict on update restrict;

