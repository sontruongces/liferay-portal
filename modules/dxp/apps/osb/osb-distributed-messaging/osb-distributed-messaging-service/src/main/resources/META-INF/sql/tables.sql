create table DM_QueuedMessage (
	mvccVersion LONG default 0 not null,
	queuedMessageId LONG not null primary key,
	createDate DATE null,
	messageBrokerClassName VARCHAR(255) null,
	topic VARCHAR(150) null,
	messageObject BLOB
);