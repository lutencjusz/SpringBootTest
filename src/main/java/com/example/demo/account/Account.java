package com.example.demo.account;

import java.util.UUID;

public record Account(UUID id, String name, String accountNumber) {
}
