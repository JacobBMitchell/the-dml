drop database if exists dnd_test;
create database dnd_test;
use dnd_test;

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
    campaignName varchar(50) null,
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

delimiter //
create procedure set_known_good_state()
begin

	delete from characters;
    alter table characters auto_increment = 1;
    delete from campaigns;
    alter table campaigns auto_increment = 1;
    delete from user_role;
    delete from users;
    alter table users auto_increment = 1;
    
    	-- passwords are their first name (ex: 'Matthew')
	insert into users (firstName, lastName, email, password_hash) values
		('Matthew', 'Mercer', 'matthew@mercer.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa'),
		('Marisha', 'Ray', 'marisha@ray.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa'),
		('Sam', 'Riegel', 'sam@riegel.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa'),
		('Travis', 'Willingham', 'travis@willingham.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa'),
		('Laura', 'Bailey', 'laura@bailey.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa'),
		('Jacob', 'Mitchell', 'jacob@mitchell.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa'),
		('Tyler', 'Litz', 'tyler@litz.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa');
		
	insert into user_role (userId, roleId) values
		( 1, 2),
		( 2, 1),
		( 3, 1),
		( 4, 1),
		( 5, 1),
		( 6, 3),
		( 7, 3),
		( 5, 2);
    
    insert into campaigns (dmId, campaignName, notes) values
		(1, 'Vox Machina', 'List of Notes'),
        (5, 'Second campaign', null);
	
    insert into characters (characterName, userId, campaignId, currentHealth, maxHealth, dndClass, race, characterLevel, armorClass, gold, speed,
							str, dex, con, intel, wis, cha, savingStr, savingDex, savingCon, savingIntel, savingWis, savingCha)
		values
		('Keyleth', 2, 1, 63, 70, 'druid', 'half-elf', 9, 17, 10000, 30, 14, 15, 14, 15, 22, 10, 0, 0, 0, 1, 1, 0),
        ('Scanlan Shorthalt', 3, 1, 51, 58, 'Bard', 'gnome', 9, 17, 10000, 25, 13, 11, 15, 14, 7, 20, 0, 1, 0, 0, 0, 1),
        ('Grog Strongjaw', 4, 1, 116, 116, 'barbarian', 'goliath', 9, 19, 10000, 50, 19, 15, 20, 6, 10, 13, 1, 0, 1, 0, 0, 0),
        ("Vex'ahlia", 5, 1, 62, 71, 'ranger', 'half-elf', 9, 19, 10000, 30, 7, 20, 10, 14, 14, 17, 1, 1, 0, 0, 0, 0),
		('Keyleth the Second', 2, 2, 63, 70, 'druid', 'half-elf', 9, 17, 10000, 30, 14, 15, 14, 15, 22, 10, 0, 0, 0, 1, 1, 0);


end //
-- 4. Change the statement terminator back to the original.
delimiter ;

insert into roles (roleName) values
	('PLAYER'),
    ('DM'),
    ('ADMIN');

call set_known_good_state();