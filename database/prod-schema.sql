drop database if exists dnd;
create database dnd;
use dnd;

create table users (
	userId int primary key auto_increment,
	firstName varchar(25) not null,
    lastName varchar(50) not null,
    email varchar(50) not null,
    password_hash varchar(2048) not null
);

create table roles (
	roleId int primary key auto_increment,
    roleName varchar(25) not null
);

create table user_role (
	userId int not null,
    roleId int not null,
    constraint pk_user_role
		primary key (userId, roleId),
	constraint fk_user_role_userId
		foreign key (userId)
        references users(userId),
	constraint fk_user_role_roleId
		foreign key (roleId)
        references roles(roleId)
);

create table campaigns (
	campaignId int primary key auto_increment,
    dmId int not null,
    notes text null,
    constraint fk_campaigns_dmId
		foreign key (dmId)
        references users(userId)
);

create table characters (
	characterId int primary key auto_increment,
    characterName varchar(50) not null,
    userId int not null,
    campaignId int null,
    currentHealth int not null,
    maxHealth int not null,
    dndClass varchar(20) not null,
    race varchar(20) not null,
    characterLevel int not null,
    armorClass int not null,
    gold int not null,
    speed int not null,
    str int not null,
    dex int not null,
    con int not null,
    intel int not null,
    wis int not null,
    cha int not null,
    savingStr bit not null,
    savingDex bit not null,
    savingCon bit not null,
    savingIntel bit not null,
    savingWis bit not null,
    savingCha bit not null,
    acrobatics bit null,
    animalHandling bit null,
    arcana bit null,
    athletics bit null,
    deception bit null,
    `history` bit null,
    insight bit null,
    intimidation bit null,
    investigation bit null,
    medicine bit null,
    nature bit null,
    perception bit null,
    performance bit null,
    persuasion bit null,
    religion bit null,
    sleightOfHand bit null,
    stealth bit null,
    survival bit null,
	constraint fk_characters_userId
		foreign key (userId)
        references users(userId),
    constraint fk_characters_campaignId
		foreign key (campaignId)
        references campaigns(campaignId)
);