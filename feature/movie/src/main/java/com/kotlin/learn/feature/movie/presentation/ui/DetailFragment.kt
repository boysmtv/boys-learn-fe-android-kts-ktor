package com.kotlin.learn.feature.movie.presentation.ui

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.util.ImageUtil
import com.kotlin.learn.core.common.util.event.invokeDataStoreEvent
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.model.FavouriteDataModel
import com.kotlin.learn.core.model.Genres
import com.kotlin.learn.core.model.MovieDetailModel
import com.kotlin.learn.core.model.RecentDataModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.model.VideoDetailModel
import com.kotlin.learn.core.nav.navigator.MovieNavigator
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.Constant.EMPTY_STRING
import com.kotlin.learn.core.utilities.Constant.SIXTY
import com.kotlin.learn.core.utilities.Constant.WARNING_MESSAGE
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.core.utilities.setTextAnimation
import com.kotlin.learn.feature.movie.R
import com.kotlin.learn.feature.movie.adapter.DetailCreditsAdapter
import com.kotlin.learn.feature.movie.databinding.FragmentDetailBinding
import com.kotlin.learn.feature.movie.presentation.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import javax.inject.Inject


@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    @Inject
    lateinit var movieNavigator: MovieNavigator

    private val viewModel: DetailViewModel by viewModels()

    private val args: DetailFragmentArgs by navArgs()

    private lateinit var userModel: UserModel

    private lateinit var movieModel: MovieDetailModel

    private lateinit var favouriteModel: FavouriteDataModel

    private lateinit var recentDataModel: RecentDataModel

    private var movieKey = EMPTY_STRING

    private var movieId = EMPTY_STRING

    private var isSaved: Boolean = false

    private var isPermissionSaved: Boolean = true

    override fun setupView() {
        setupInit()
        subscribeDetail()
        loadArguments()
        loadUser()
        setupVpCredits()
        setupListener()
    }

    private fun setupInit() {
        userModel = UserModel()
        movieModel = MovieDetailModel()
    }

    private fun setupListener() = with(binding) {

        clDetailHeaderFavouriteIcon.setOnClickListener {
            if (isPermissionSaved)
                if (isSaved) {
                    isSaved = false
                    setChangeUiFavourite()
                    addOrRemoveToFavourite(isAdded = false)
                } else {
                    isSaved = true
                    setChangeUiFavourite()
                    addOrRemoveToFavourite(isAdded = true)
                }
            else
                showDialogGeneralError(
                    WARNING_MESSAGE,
                    "This feature is disabled in your settings, please check your settings"
                )
        }

        btnDetailPlayNow.setOnClickListener {
            if (movieKey != EMPTY_STRING)
                movieNavigator.fromDetailToVideos(this@DetailFragment, movieKey)
            else
                Toast.makeText(requireContext(), "The trailer is not available", Toast.LENGTH_SHORT).show()
        }

        tvDetailCastSeeAll.setOnClickListener {
            movieNavigator.fromDetailToSeeAllCredits(this@DetailFragment, movieId)
        }

    }

    private fun setChangeUiFavourite() = with(binding) {
        if (isSaved) {
            ImageUtil.imageViewAnimatedChange(
                requireContext(),
                ivDetailHeaderFavouriteIcon,
                BitmapFactory.decodeResource(requireContext().resources, R.drawable.ic_saved)
            )
            tvDetailHeaderFavouriteTitle.setTextAnimation(getString(R.string.saved))
        } else {
            ImageUtil.imageViewAnimatedChange(
                requireContext(),
                ivDetailHeaderFavouriteIcon,
                BitmapFactory.decodeResource(requireContext().resources, R.drawable.ic_favourite)
            )
            tvDetailHeaderFavouriteTitle.setTextAnimation(getString(R.string.favourite))
        }
    }

    private fun subscribeDetail() = with(binding) {
        viewModel.detailMovies.launch(this@DetailFragment) {
            handleMovieResult(it)
        }

        viewModel.detailVideos.launch(this@DetailFragment) {
            handleVideoResult(it)
        }
    }

    private fun handleMovieResult(result: Result<MovieDetailModel>) = with(binding) {
        when (result) {
            is Result.Loading -> viewAnimator.displayedChild = Constant.ZERO
            is Result.Success -> {
                viewAnimator.displayedChild = Constant.ONE
                setupDetailMovie(result.data)
            }
            is Result.Error -> viewAnimator.displayedChild = Constant.TWO
            else -> Unit
        }
    }

    private fun handleVideoResult(result: Result<VideoDetailModel>) {
        if (result !is Result.Success) return

        result.data.results.firstOrNull { it.name == "Official Trailer" && it.key != null }?.key?.let {
            movieKey = it
            return
        }

        result.data.results.firstOrNull { it.key != null }?.key?.let {
            movieKey = it
        }
    }

    private fun setupDetailMovie(movieDetailModel: MovieDetailModel) = with(binding) {
        movieModel = movieDetailModel
        movieModel.let {
            tvDetailHeaderTitle.text = it.title
            tvDetailHeaderRuleGenres.text = convertGenres(it.genres)
            tvDetailHeaderRuleTime.text = calculateRuntime(it.runtime)
            tvDetailHeaderRuleYear.text = convertDatetimeToDate(it.releaseDate)
            tvDetailHeaderDesc.text = it.overview
            setupThumbnail(it)
        }
        setupFavouriteAndRecentModel()
    }

    private fun setupVpCredits() = with(binding) {
        val creditsAdapter = DetailCreditsAdapter(activity)
        creditsAdapter.addFragment(
            fragment = CreditsFragment(
                creditsCategory = CreditsCategory.CAST,
                movieId = movieId
            ),
            title = "Cast"
        )
        creditsAdapter.addFragment(
            fragment = CreditsFragment(
                creditsCategory = CreditsCategory.CREW,
                movieId = movieId
            ),
            title = "Director & Crew"
        )

        tlDetailCredits.apply {
            tabGravity = TabLayout.GRAVITY_FILL
        }
        vpDetailCredits.apply {
            adapter = creditsAdapter
            currentItem = Constant.ZERO
            isUserInputEnabled = Constant.FALSE
        }
        TabLayoutMediator(tlDetailCredits, vpDetailCredits) { tab, position ->
            tab.text = creditsAdapter.getTabTitle(position)
        }.attach()
    }

    @SuppressLint("SimpleDateFormat")
    private fun convertDatetimeToDate(releaseDate: String?): String {
        releaseDate?.let {
            val currFormatter = SimpleDateFormat("yyyy")
            return currFormatter.format(currFormatter.parse(releaseDate)!!)
        }
        return EMPTY_STRING
    }

    private fun loadArguments() {
        movieId = args.movieId
        setupRemoteData(movieId)
    }

    private fun setupRemoteData(movieId: String) {
        viewModel.getDetailMovies(movieId = movieId)
        viewModel.getDetailVideos(movieId = movieId)
    }

    private fun loadUser() = with(viewModel) {
        fetchUserFromDatastore().launch(requireActivity()) { event ->
            invokeDataStoreEvent(
                event,
                isFetched = { data ->
                    data?.let { model ->
                        userModel = model
                        setupEnableAddToFavourite()
                    }
                },
            )
        }
        setupFavourite()
    }

    private fun setupFavourite() {
        isSaved = userModel.favourite.any { it.id.toString() == movieId }
        if (isSaved) {
            setChangeUiFavourite()
        }
    }

    private fun calculateRuntime(timeRuntime: Int?): String {
        timeRuntime?.let {
            var startTime = timeRuntime
            var startHour = Constant.ZERO
            for (i in 0..startTime) {
                if (startTime < SIXTY) {
                    break
                } else {
                    startTime -= SIXTY
                    startHour++
                    continue
                }
            }
            return "${startHour}h ${timeRuntime % SIXTY}m"
        }
        return EMPTY_STRING
    }

    private fun convertGenres(genres: ArrayList<Genres>): String {
        return genres[0].name.toString()
    }

    private fun setupThumbnail(movie: MovieDetailModel) = with(binding) {
        movie.backdropPath?.let {
            Glide.with(this@DetailFragment)
                .load("${Constant.BASE_URL_IMAGE_500}${it}")
                .into(ivDetailPlay)
        }
    }

    private fun addOrRemoveToFavourite(isAdded: Boolean) = with(viewModel) {
        fetchUserFromDatastore().launch(requireActivity()) { event ->
            invokeDataStoreEvent(
                event,
                isFetched = { data ->
                    data?.let { model ->
                        userModel = model.apply {
                            favourite = model.favourite.apply {
                                if (isAdded) add(favouriteModel) else remove(favouriteModel)
                            }
                        }

                        storeUserToDatastore(jsonUtil.toJson(userModel)).launch(requireActivity()) { event ->
                            invokeDataStoreEvent(event,
                                isStored = {
                                    userModel.id?.let { id ->
                                        updateUserToFirestore(
                                            id = id,
                                            model = mapOf(
                                                "favourite" to userModel.favourite
                                            ),
                                            onLoad = { },
                                            onSuccess = { },
                                            onError = { }
                                        )
                                    }
                                }
                            )
                        }
                    }
                },
            )
        }
    }

    private fun addToRecentMovie() = with(viewModel) {
        fetchUserFromDatastore().launch(requireActivity()) { event ->
            invokeDataStoreEvent(
                event,
                isFetched = { data ->
                    data?.let { model ->
                        if (model.recent.isNotEmpty()) {
                            var itSame = false
                            for (recent in model.recent) {
                                if (recent == recentDataModel) {
                                    itSame = true
                                    break
                                }
                            }
                            userModel = model.apply {
                                recent = model.recent.apply {
                                    if (itSame) remove(recentDataModel)
                                    add(0, recentDataModel)
                                }
                            }
                        } else {
                            userModel = model.apply {
                                recent = model.recent.apply {
                                    add(0, recentDataModel)
                                }
                            }
                        }

                        storeUserToDatastore(jsonUtil.toJson(userModel)).launch(requireActivity()) { event ->
                            invokeDataStoreEvent(event,
                                isStored = {
                                    userModel.id?.let { id ->
                                        updateUserToFirestore(
                                            id = id,
                                            model = mapOf(
                                                "recent" to userModel.recent
                                            ),
                                            onLoad = { },
                                            onSuccess = { },
                                            onError = { }
                                        )
                                    }
                                }
                            )
                        }
                    }
                },
            )
        }

    }

    private fun setupFavouriteAndRecentModel() {
        favouriteModel = FavouriteDataModel().apply {
            id = movieModel.id
            originalTitle = movieModel.originalTitle
            backdropPath = movieModel.backdropPath
            posterPath = movieModel.posterPath
            title = movieModel.title
        }
        recentDataModel = RecentDataModel().apply {
            id = movieModel.id
            originalTitle = movieModel.originalTitle
            backdropPath = movieModel.backdropPath
            posterPath = movieModel.posterPath
            imdbId = movieModel.imdbId
            title = movieModel.title
        }
        addToRecentMovie()
    }

    private fun setupEnableAddToFavourite() {
        isPermissionSaved = userModel.profile?.setting?.favourite == true
    }

}