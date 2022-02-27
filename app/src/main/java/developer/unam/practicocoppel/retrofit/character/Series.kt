package developer.unam.practicocoppel.retrofit.character

data class Series(
    val available: String,
    val collectionURI: String,
    val items: List<Item>,
    val returned: String
)