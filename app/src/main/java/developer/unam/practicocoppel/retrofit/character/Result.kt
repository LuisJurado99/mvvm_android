package developer.unam.practicocoppel.retrofit.character

import android.os.Parcel
import android.os.Parcelable

data class Result(
    val id: Int,
    val name: String?,
    val description: String?,
    val comics: Comics?,
    val series: Series?,
    val stories: Stories?,
    val events: Events?,
    val thumbnail: Thumbnail?,
    val urls: List<Url>?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Comics::class.java.classLoader),
        parcel.readParcelable(Series::class.java.classLoader),
        parcel.readParcelable(Stories::class.java.classLoader),
        parcel.readParcelable(Events::class.java.classLoader),
        parcel.readParcelable(Thumbnail::class.java.classLoader),
        parcel.createTypedArrayList(Url)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeParcelable(comics, flags)
        parcel.writeParcelable(series, flags)
        parcel.writeParcelable(stories, flags)
        parcel.writeParcelable(events, flags)
        parcel.writeParcelable(thumbnail, flags)
        parcel.writeTypedList(urls)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }
}