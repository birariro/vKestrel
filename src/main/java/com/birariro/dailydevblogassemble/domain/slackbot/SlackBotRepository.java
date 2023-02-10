package com.birariro.dailydevblogassemble.domain.slackbot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SlackBotRepository extends JpaRepository<SlackBot, UUID> {
}
