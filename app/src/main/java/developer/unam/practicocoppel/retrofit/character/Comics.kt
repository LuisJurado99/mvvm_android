package developer.unam.practicocoppel.retrofit.character

data class Comics(
    val available: Int,
    val items: List<Item>,
    val returned: Int
)