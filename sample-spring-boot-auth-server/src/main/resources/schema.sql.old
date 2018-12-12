create table oauth_client_details (
  client_id VARCHAR2(255) PRIMARY KEY,
  resource_ids VARCHAR2(255),
  client_secret VARCHAR2(255),
  scope VARCHAR2(255),
  authorized_grant_types VARCHAR2(255),
  web_server_redirect_uri VARCHAR2(255),
  authorities VARCHAR2(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR2(2000),
  autoapprove VARCHAR2(255)
);

create table oauth_client_token (
  token_id VARCHAR2(255),
  token VARCHAR2(2000),
  authentication_id VARCHAR2(255) PRIMARY KEY,
  user_name VARCHAR2(255),
  client_id VARCHAR2(255)
);


create table oauth_access_token (
  token_id VARCHAR2(255),
  token VARCHAR2(2000),
  authentication_id VARCHAR2(255) PRIMARY KEY,
  user_name VARCHAR2(255),
  client_id VARCHAR2(255),
  authentication VARCHAR2(2000),
  refresh_token VARCHAR2(255)
);

create table oauth_refresh_token (
  token_id VARCHAR2(255),
  token VARCHAR2(2000),
  authentication VARCHAR2(2000)
);

create table oauth_code (
  code VARCHAR2(255), authentication VARCHAR2(2000)
);

create table oauth_approvals (
    userId VARCHAR2(255),
    clientId VARCHAR2(255),
    scope VARCHAR2(255),
    status VARCHAR2(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
);

create table ClientDetails (
  appId VARCHAR2(255) PRIMARY KEY,
  resourceIds VARCHAR2(255),
  appSecret VARCHAR2(255),
  scope VARCHAR2(255),
  grantTypes VARCHAR2(255),
  redirectUrl VARCHAR2(255),
  authorities VARCHAR2(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR2(2000),
  autoApproveScopes VARCHAR2(255)
);