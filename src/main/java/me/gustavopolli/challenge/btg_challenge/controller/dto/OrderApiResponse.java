package me.gustavopolli.challenge.btg_challenge.controller.dto;


import java.awt.print.Pageable;
import java.util.List;

public record OrderApiResponse<T>(List<T> orders, Pageable pageable) {
}
