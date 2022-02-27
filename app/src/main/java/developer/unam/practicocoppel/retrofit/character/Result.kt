package developer.unam.practicocoppel.retrofit.character

data class Result(
    val id: Int,
    val name: String,
    val description: String,
    val comics: Comics,
    val series: Series,
    val stories: Stories,
    val events: Events,
    val thumbnail: Thumbnail,
    val urls: List<Url>
)