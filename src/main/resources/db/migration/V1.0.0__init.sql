-- `vk-db`.BATCH_JOB_EXECUTION_SEQ definition

CREATE TABLE `BATCH_JOB_EXECUTION_SEQ` (
                                           `ID` bigint(20) NOT NULL,
                                           `UNIQUE_KEY` char(1) COLLATE utf8mb4_unicode_ci NOT NULL,
                                           UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- `vk-db`.BATCH_JOB_INSTANCE definition

CREATE TABLE `BATCH_JOB_INSTANCE` (
                                      `JOB_INSTANCE_ID` bigint(20) NOT NULL,
                                      `VERSION` bigint(20) DEFAULT NULL,
                                      `JOB_NAME` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                                      `JOB_KEY` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
                                      PRIMARY KEY (`JOB_INSTANCE_ID`),
                                      UNIQUE KEY `JOB_INST_UN` (`JOB_NAME`,`JOB_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- `vk-db`.BATCH_JOB_SEQ definition

CREATE TABLE `BATCH_JOB_SEQ` (
                                 `ID` bigint(20) NOT NULL,
                                 `UNIQUE_KEY` char(1) COLLATE utf8mb4_unicode_ci NOT NULL,
                                 UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- `vk-db`.BATCH_STEP_EXECUTION_SEQ definition

CREATE TABLE `BATCH_STEP_EXECUTION_SEQ` (
                                            `ID` bigint(20) NOT NULL,
                                            `UNIQUE_KEY` char(1) COLLATE utf8mb4_unicode_ci NOT NULL,
                                            UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- `vk-db`.tb_library definition

CREATE TABLE `tb_library` (
                              `id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                              `create_at` datetime(6) DEFAULT NULL,
                              `state` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                              `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                              `origin` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                              `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                              `url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- `vk-db`.tb_member definition

CREATE TABLE `tb_member` (
                             `id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                             `create_at` datetime(6) DEFAULT NULL,
                             `state` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `channel` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `token` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- `vk-db`.BATCH_JOB_EXECUTION definition

CREATE TABLE `BATCH_JOB_EXECUTION` (
                                       `JOB_EXECUTION_ID` bigint(20) NOT NULL,
                                       `VERSION` bigint(20) DEFAULT NULL,
                                       `JOB_INSTANCE_ID` bigint(20) NOT NULL,
                                       `CREATE_TIME` datetime(6) NOT NULL,
                                       `START_TIME` datetime(6) DEFAULT NULL,
                                       `END_TIME` datetime(6) DEFAULT NULL,
                                       `STATUS` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                       `EXIT_CODE` varchar(2500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                       `EXIT_MESSAGE` varchar(2500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                       `LAST_UPDATED` datetime(6) DEFAULT NULL,
                                       `JOB_CONFIGURATION_LOCATION` varchar(2500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                       PRIMARY KEY (`JOB_EXECUTION_ID`),
                                       KEY `JOB_INST_EXEC_FK` (`JOB_INSTANCE_ID`),
                                       CONSTRAINT `JOB_INST_EXEC_FK` FOREIGN KEY (`JOB_INSTANCE_ID`) REFERENCES `BATCH_JOB_INSTANCE` (`JOB_INSTANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- `vk-db`.BATCH_JOB_EXECUTION_CONTEXT definition

CREATE TABLE `BATCH_JOB_EXECUTION_CONTEXT` (
                                               `JOB_EXECUTION_ID` bigint(20) NOT NULL,
                                               `SHORT_CONTEXT` varchar(2500) COLLATE utf8mb4_unicode_ci NOT NULL,
                                               `SERIALIZED_CONTEXT` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                               PRIMARY KEY (`JOB_EXECUTION_ID`),
                                               CONSTRAINT `JOB_EXEC_CTX_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- `vk-db`.BATCH_JOB_EXECUTION_PARAMS definition

CREATE TABLE `BATCH_JOB_EXECUTION_PARAMS` (
                                              `JOB_EXECUTION_ID` bigint(20) NOT NULL,
                                              `TYPE_CD` varchar(6) COLLATE utf8mb4_unicode_ci NOT NULL,
                                              `KEY_NAME` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                                              `STRING_VAL` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                              `DATE_VAL` datetime(6) DEFAULT NULL,
                                              `LONG_VAL` bigint(20) DEFAULT NULL,
                                              `DOUBLE_VAL` double DEFAULT NULL,
                                              `IDENTIFYING` char(1) COLLATE utf8mb4_unicode_ci NOT NULL,
                                              KEY `JOB_EXEC_PARAMS_FK` (`JOB_EXECUTION_ID`),
                                              CONSTRAINT `JOB_EXEC_PARAMS_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- `vk-db`.BATCH_STEP_EXECUTION definition

CREATE TABLE `BATCH_STEP_EXECUTION` (
                                        `STEP_EXECUTION_ID` bigint(20) NOT NULL,
                                        `VERSION` bigint(20) NOT NULL,
                                        `STEP_NAME` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                                        `JOB_EXECUTION_ID` bigint(20) NOT NULL,
                                        `START_TIME` datetime(6) NOT NULL,
                                        `END_TIME` datetime(6) DEFAULT NULL,
                                        `STATUS` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                        `COMMIT_COUNT` bigint(20) DEFAULT NULL,
                                        `READ_COUNT` bigint(20) DEFAULT NULL,
                                        `FILTER_COUNT` bigint(20) DEFAULT NULL,
                                        `WRITE_COUNT` bigint(20) DEFAULT NULL,
                                        `READ_SKIP_COUNT` bigint(20) DEFAULT NULL,
                                        `WRITE_SKIP_COUNT` bigint(20) DEFAULT NULL,
                                        `PROCESS_SKIP_COUNT` bigint(20) DEFAULT NULL,
                                        `ROLLBACK_COUNT` bigint(20) DEFAULT NULL,
                                        `EXIT_CODE` varchar(2500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                        `EXIT_MESSAGE` varchar(2500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                        `LAST_UPDATED` datetime(6) DEFAULT NULL,
                                        PRIMARY KEY (`STEP_EXECUTION_ID`),
                                        KEY `JOB_EXEC_STEP_FK` (`JOB_EXECUTION_ID`),
                                        CONSTRAINT `JOB_EXEC_STEP_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- `vk-db`.BATCH_STEP_EXECUTION_CONTEXT definition

CREATE TABLE `BATCH_STEP_EXECUTION_CONTEXT` (
                                                `STEP_EXECUTION_ID` bigint(20) NOT NULL,
                                                `SHORT_CONTEXT` varchar(2500) COLLATE utf8mb4_unicode_ci NOT NULL,
                                                `SERIALIZED_CONTEXT` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                                PRIMARY KEY (`STEP_EXECUTION_ID`),
                                                CONSTRAINT `STEP_EXEC_CTX_FK` FOREIGN KEY (`STEP_EXECUTION_ID`) REFERENCES `BATCH_STEP_EXECUTION` (`STEP_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- `vk-db`.tb_document definition

CREATE TABLE `tb_document` (
                               `id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                               `create_at` datetime(6) DEFAULT NULL,
                               `state` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               `author` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               `send_state` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               `library_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               KEY `FK269m6s7lhvrk53ljnbrx7cf6y` (`library_id`),
                               CONSTRAINT `FK269m6s7lhvrk53ljnbrx7cf6y` FOREIGN KEY (`library_id`) REFERENCES `tb_library` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;