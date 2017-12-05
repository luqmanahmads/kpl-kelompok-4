/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     12/5/2017 6:49:15 PM                         */
/*==============================================================*/


drop table if exists conversation;

drop table if exists devices;

drop table if exists friends;

drop table if exists messages;

drop table if exists participants;

drop table if exists users;

/*==============================================================*/
/* Table: conversation                                          */
/*==============================================================*/
create table conversation
(
   id                   int not null,
   title                varchar(40),
   channel_id           varchar(50),
   created_at           datetime,
   updated_at           datetime,
   deleted_at           datetime,
   primary key (id)
);

alter table conversation comment 'Tabel yang menampung "room".
User ke user dihitung 1 c';

/*==============================================================*/
/* Table: devices                                               */
/*==============================================================*/
create table devices
(
   id                   varchar(50) not null,
   users_id             int,
   device_id            varchar(20),
   type                 varchar(10),
   device_token         varchar(50),
   created_at           datetime,
   updated_at           datetime,
   deleted_at           datetime,
   primary key (id)
);

alter table devices comment 'Tabel yang berisi devices dari setiap user.
Diasumsika';

/*==============================================================*/
/* Table: friends                                               */
/*==============================================================*/
create table friends
(
   id                   int not null,
   user_id              int,
   friend_id            int not null,
   created_at           datetime,
   updated_at           datetime,
   deleted_at           datetime,
   primary key (id)
);

/*==============================================================*/
/* Table: messages                                              */
/*==============================================================*/
create table messages
(
   id                   int not null,
   participants_id      int,
   conversation_id      int,
   users_id             int,
   message_type         varchar(20) not null,
   message              varchar(1000),
   attachment_url       varchar(255),
   created_at           datetime,
   updated_at           datetime,
   deleted_at           datetime,
   primary key (id)
);

alter table messages comment 'message_type bisa jadi plain text atau gambar
attachme';

/*==============================================================*/
/* Table: participants                                          */
/*==============================================================*/
create table participants
(
   id                   int not null,
   conversation_id      int,
   users_id             int,
   created_at           datetime,
   updated_at           datetime,
   deleted_at           datetime,
   primary key (id)
);

/*==============================================================*/
/* Table: users                                                 */
/*==============================================================*/
create table users
(
   id                   int not null,
   phone                varchar(16),
   email                varchar(255),
   password             varchar(255),
   name                 varchar(255),
   verification_code    varchar(40),
   is_active            bool,
   created_at           datetime,
   updated_at           datetime,
   deleted_at           datetime,
   primary key (id)
);

alter table devices add constraint FK_user_has_device foreign key (users_id)
      references users (id) on delete restrict on update restrict;

alter table friends add constraint FK_friend_of_user foreign key (user_id)
      references users (id) on delete restrict on update restrict;

alter table friends add constraint FK_user_has_friends foreign key (friend_id)
      references users (id) on delete restrict on update restrict;

alter table messages add constraint FK_conversation_has_messages foreign key (conversation_id)
      references conversation (id) on delete restrict on update restrict;

alter table messages add constraint FK_participant_message foreign key (participants_id)
      references participants (id) on delete restrict on update restrict;

alter table messages add constraint FK_sender foreign key (users_id)
      references users (id) on delete restrict on update restrict;

alter table participants add constraint FK_conversation_participants foreign key (conversation_id)
      references conversation (id) on delete restrict on update restrict;

alter table participants add constraint FK_users_participants foreign key (users_id)
      references users (id) on delete restrict on update restrict;

