package com.shopping.app.dbo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.app.entity.Offer;

public interface OfferRepository extends JpaRepository<Offer, UUID> {

}
