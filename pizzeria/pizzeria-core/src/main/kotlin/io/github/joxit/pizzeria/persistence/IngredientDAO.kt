package io.github.joxit.pizzeria.persistence

import io.github.joxit.pizzeria.model.Ingredient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@EnableJpaRepositories
interface IngredientDAO : JpaRepository<Ingredient, String>