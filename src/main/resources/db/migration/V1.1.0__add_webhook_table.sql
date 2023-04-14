-- `vk-db`.tb_web_hook definition

CREATE TABLE `tb_web_hook` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `create_at` datetime(6) DEFAULT NULL,
                               `state` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               `url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `UK_dtan8w9woxuqm80se4b3ckjts` (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;