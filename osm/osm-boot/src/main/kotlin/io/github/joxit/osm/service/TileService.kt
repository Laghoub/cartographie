package io.github.joxit.osm.service

import io.github.joxit.osm.controller.handler.GlobalExceptionHandler
import io.github.joxit.osm.model.Tile
import io.github.joxit.osm.utils.Svg
import java.io.IOException
import mil.nga.sf.geojson.GeoJsonObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import org.springframework.util.StreamUtils
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.IllegalArgumentException
import java.util.stream.Collectors

/**
 * Service pour retourner les tuiles.
 *
 * @author Jones Magloire @Joxit
 * @since 2019-11-03
 */
@Service
class TileService {

  /**
   * Ici il faut prendre les coordonnées de la tuile et renvoyer la donnée PNG associée.
   * Vous pouvez y ajouter des fonctionnalités en plus pour améliorer les perfs.
   *
   * @param tile qu'il faut renvoyer
   * @return le byte array au format png
   */
  fun getTile(tile: Tile): ByteArray? {

    if (tile.z < 0 || tile.x < 0 || tile.y < 0 ||  tile.z > 24) {
      throw IllegalArgumentException("Coordonnées de tuile non valides")
    }else{
      return Svg.getTile(tile)
    }




  }

  /**
   * Attention, il ne faut pas utiliser des fonctions utilisant un fichier de votre file system comme File ou FileInputStream.
   * Vous devez utiliser le classpath du projet à la place Cf Resources de Spring.
   *
   * @return le contenu du fichier prefectures.geojson
   */

  @Value("\${classpath:prefectures.geojson}")
  private lateinit var geoJsonFilePath: String
  @Throws(IOException::class)
  fun getPrefectures(): String {

    try {
      val resource = ClassPathResource(geoJsonFilePath)
      val reader = InputStreamReader(resource.inputStream)
      return reader.readText()
    } catch (e: IOException) {
      throw IllegalStateException(e)
    }
  }

  /**
   * Il faudra créer votre DAO pour récuperer les données.
   * Utilisez ce que vous voulez pour faire le DAO.
   *
   * @return les éléments contenus dans la base de données
   */
  fun getPOIs(): GeoJsonObject {
    TODO("À implémenter, lisez la JAVADOC et les consignes !")
  }
}
