package com.kotlin.learn.catalog.core.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\bO\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 n2\u00020\u0001:\u0002mnB\u00c7\u0001\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0001\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u00a2\u0006\u0002\u0010\u0018B\u00b1\u0001\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0019J\u0010\u0010R\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001dJ\u000b\u0010S\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u0010\u0010U\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001dJ\u0010\u0010V\u001a\u0004\u0018\u00010\u000fH\u00c6\u0003\u00a2\u0006\u0002\u0010<J\u0010\u0010W\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010-J\u000b\u0010X\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000f\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00030\tH\u00c6\u0003J\u0010\u0010Z\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010-J\u000b\u0010[\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u0010\u0010^\u001a\u0004\u0018\u00010\u000fH\u00c6\u0003\u00a2\u0006\u0002\u0010<J\u000b\u0010_\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u00ba\u0001\u0010`\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010aJ\u0013\u0010b\u001a\u00020\u00052\b\u0010c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010d\u001a\u00020\u0003H\u00d6\u0001J\t\u0010e\u001a\u00020\u0007H\u00d6\u0001J!\u0010f\u001a\u00020g2\u0006\u0010h\u001a\u00020\u00002\u0006\u0010i\u001a\u00020j2\u0006\u0010k\u001a\u00020lH\u00c7\u0001R(\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0016\n\u0002\u0010 \u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR&\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0014\n\u0000\u0012\u0004\b!\u0010\u001b\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R*\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0014\n\u0000\u0012\u0004\b&\u0010\u001b\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R(\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0016\n\u0002\u00100\u0012\u0004\b+\u0010\u001b\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R&\u0010\u000b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0014\n\u0000\u0012\u0004\b1\u0010\u001b\u001a\u0004\b2\u0010#\"\u0004\b3\u0010%R&\u0010\f\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0014\n\u0000\u0012\u0004\b4\u0010\u001b\u001a\u0004\b5\u0010#\"\u0004\b6\u0010%R&\u0010\r\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0014\n\u0000\u0012\u0004\b7\u0010\u001b\u001a\u0004\b8\u0010#\"\u0004\b9\u0010%R(\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0016\n\u0002\u0010?\u0012\u0004\b:\u0010\u001b\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R&\u0010\u0010\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0014\n\u0000\u0012\u0004\b@\u0010\u001b\u001a\u0004\bA\u0010#\"\u0004\bB\u0010%R&\u0010\u0011\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0014\n\u0000\u0012\u0004\bC\u0010\u001b\u001a\u0004\bD\u0010#\"\u0004\bE\u0010%R&\u0010\u0012\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0014\n\u0000\u0012\u0004\bF\u0010\u001b\u001a\u0004\bG\u0010#\"\u0004\bH\u0010%R(\u0010\u0013\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0016\n\u0002\u0010 \u0012\u0004\bI\u0010\u001b\u001a\u0004\bJ\u0010\u001d\"\u0004\bK\u0010\u001fR(\u0010\u0014\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0016\n\u0002\u0010?\u0012\u0004\bL\u0010\u001b\u001a\u0004\bM\u0010<\"\u0004\bN\u0010>R(\u0010\u0015\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0016\n\u0002\u00100\u0012\u0004\bO\u0010\u001b\u001a\u0004\bP\u0010-\"\u0004\bQ\u0010/\u00a8\u0006o"}, d2 = {"Lcom/kotlin/learn/catalog/core/model/NetworkMovieData;", "", "seen1", "", "adult", "", "backdropPath", "", "genreIds", "", "id", "originalLanguage", "originalTitle", "overview", "popularity", "", "posterPath", "releaseDate", "title", "video", "voteAverage", "voteCount", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Boolean;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Double;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Double;Ljava/lang/Integer;)V", "getAdult$annotations", "()V", "getAdult", "()Ljava/lang/Boolean;", "setAdult", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getBackdropPath$annotations", "getBackdropPath", "()Ljava/lang/String;", "setBackdropPath", "(Ljava/lang/String;)V", "getGenreIds$annotations", "getGenreIds", "()Ljava/util/List;", "setGenreIds", "(Ljava/util/List;)V", "getId$annotations", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getOriginalLanguage$annotations", "getOriginalLanguage", "setOriginalLanguage", "getOriginalTitle$annotations", "getOriginalTitle", "setOriginalTitle", "getOverview$annotations", "getOverview", "setOverview", "getPopularity$annotations", "getPopularity", "()Ljava/lang/Double;", "setPopularity", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "getPosterPath$annotations", "getPosterPath", "setPosterPath", "getReleaseDate$annotations", "getReleaseDate", "setReleaseDate", "getTitle$annotations", "getTitle", "setTitle", "getVideo$annotations", "getVideo", "setVideo", "getVoteAverage$annotations", "getVoteAverage", "setVoteAverage", "getVoteCount$annotations", "getVoteCount", "setVoteCount", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Double;Ljava/lang/Integer;)Lcom/kotlin/learn/catalog/core/model/NetworkMovieData;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "model_debug"})
@kotlinx.serialization.Serializable
public final class NetworkMovieData {
    @org.jetbrains.annotations.NotNull
    public static final com.kotlin.learn.catalog.core.model.NetworkMovieData.Companion Companion = null;
    @org.jetbrains.annotations.Nullable
    private java.lang.Boolean adult;
    @org.jetbrains.annotations.Nullable
    private java.lang.String backdropPath;
    @org.jetbrains.annotations.NotNull
    private java.util.List<java.lang.Integer> genreIds;
    @org.jetbrains.annotations.Nullable
    private java.lang.Integer id;
    @org.jetbrains.annotations.Nullable
    private java.lang.String originalLanguage;
    @org.jetbrains.annotations.Nullable
    private java.lang.String originalTitle;
    @org.jetbrains.annotations.Nullable
    private java.lang.String overview;
    @org.jetbrains.annotations.Nullable
    private java.lang.Double popularity;
    @org.jetbrains.annotations.Nullable
    private java.lang.String posterPath;
    @org.jetbrains.annotations.Nullable
    private java.lang.String releaseDate;
    @org.jetbrains.annotations.Nullable
    private java.lang.String title;
    @org.jetbrains.annotations.Nullable
    private java.lang.Boolean video;
    @org.jetbrains.annotations.Nullable
    private java.lang.Double voteAverage;
    @org.jetbrains.annotations.Nullable
    private java.lang.Integer voteCount;
    
    @org.jetbrains.annotations.NotNull
    public final com.kotlin.learn.catalog.core.model.NetworkMovieData copy(@org.jetbrains.annotations.Nullable
    java.lang.Boolean adult, @org.jetbrains.annotations.Nullable
    java.lang.String backdropPath, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.Integer> genreIds, @org.jetbrains.annotations.Nullable
    java.lang.Integer id, @org.jetbrains.annotations.Nullable
    java.lang.String originalLanguage, @org.jetbrains.annotations.Nullable
    java.lang.String originalTitle, @org.jetbrains.annotations.Nullable
    java.lang.String overview, @org.jetbrains.annotations.Nullable
    java.lang.Double popularity, @org.jetbrains.annotations.Nullable
    java.lang.String posterPath, @org.jetbrains.annotations.Nullable
    java.lang.String releaseDate, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.Boolean video, @org.jetbrains.annotations.Nullable
    java.lang.Double voteAverage, @org.jetbrains.annotations.Nullable
    java.lang.Integer voteCount) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    @kotlin.jvm.JvmStatic
    public static final void write$Self(@org.jetbrains.annotations.NotNull
    com.kotlin.learn.catalog.core.model.NetworkMovieData self, @org.jetbrains.annotations.NotNull
    kotlinx.serialization.encoding.CompositeEncoder output, @org.jetbrains.annotations.NotNull
    kotlinx.serialization.descriptors.SerialDescriptor serialDesc) {
    }
    
    public NetworkMovieData() {
        super();
    }
    
    public NetworkMovieData(@org.jetbrains.annotations.Nullable
    java.lang.Boolean adult, @org.jetbrains.annotations.Nullable
    java.lang.String backdropPath, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.Integer> genreIds, @org.jetbrains.annotations.Nullable
    java.lang.Integer id, @org.jetbrains.annotations.Nullable
    java.lang.String originalLanguage, @org.jetbrains.annotations.Nullable
    java.lang.String originalTitle, @org.jetbrains.annotations.Nullable
    java.lang.String overview, @org.jetbrains.annotations.Nullable
    java.lang.Double popularity, @org.jetbrains.annotations.Nullable
    java.lang.String posterPath, @org.jetbrains.annotations.Nullable
    java.lang.String releaseDate, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.Boolean video, @org.jetbrains.annotations.Nullable
    java.lang.Double voteAverage, @org.jetbrains.annotations.Nullable
    java.lang.Integer voteCount) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getAdult() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "adult")
    @java.lang.Deprecated
    public static void getAdult$annotations() {
    }
    
    public final void setAdult(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getBackdropPath() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "backdrop_path")
    @java.lang.Deprecated
    public static void getBackdropPath$annotations() {
    }
    
    public final void setBackdropPath(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.Integer> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.Integer> getGenreIds() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "genre_ids")
    @java.lang.Deprecated
    public static void getGenreIds$annotations() {
    }
    
    public final void setGenreIds(@org.jetbrains.annotations.NotNull
    java.util.List<java.lang.Integer> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getId() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "id")
    @java.lang.Deprecated
    public static void getId$annotations() {
    }
    
    public final void setId(@org.jetbrains.annotations.Nullable
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getOriginalLanguage() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "original_language")
    @java.lang.Deprecated
    public static void getOriginalLanguage$annotations() {
    }
    
    public final void setOriginalLanguage(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getOriginalTitle() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "original_title")
    @java.lang.Deprecated
    public static void getOriginalTitle$annotations() {
    }
    
    public final void setOriginalTitle(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getOverview() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "overview")
    @java.lang.Deprecated
    public static void getOverview$annotations() {
    }
    
    public final void setOverview(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getPopularity() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "popularity")
    @java.lang.Deprecated
    public static void getPopularity$annotations() {
    }
    
    public final void setPopularity(@org.jetbrains.annotations.Nullable
    java.lang.Double p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPosterPath() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "poster_path")
    @java.lang.Deprecated
    public static void getPosterPath$annotations() {
    }
    
    public final void setPosterPath(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getReleaseDate() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "release_date")
    @java.lang.Deprecated
    public static void getReleaseDate$annotations() {
    }
    
    public final void setReleaseDate(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getTitle() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "title")
    @java.lang.Deprecated
    public static void getTitle$annotations() {
    }
    
    public final void setTitle(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getVideo() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "video")
    @java.lang.Deprecated
    public static void getVideo$annotations() {
    }
    
    public final void setVideo(@org.jetbrains.annotations.Nullable
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getVoteAverage() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "vote_average")
    @java.lang.Deprecated
    public static void getVoteAverage$annotations() {
    }
    
    public final void setVoteAverage(@org.jetbrains.annotations.Nullable
    java.lang.Double p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getVoteCount() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "vote_count")
    @java.lang.Deprecated
    public static void getVoteCount$annotations() {
    }
    
    public final void setVoteCount(@org.jetbrains.annotations.Nullable
    java.lang.Integer p0) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00c6\u0001\u00a8\u0006\u0006"}, d2 = {"Lcom/kotlin/learn/catalog/core/model/NetworkMovieData$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/kotlin/learn/catalog/core/model/NetworkMovieData;", "model_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final kotlinx.serialization.KSerializer<com.kotlin.learn.catalog.core.model.NetworkMovieData> serializer() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tH\u00d6\u0001\u00a2\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u00d6\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VX\u00d6\u0005\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"com/kotlin/learn/catalog/core/model/NetworkMovieData.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/kotlin/learn/catalog/core/model/NetworkMovieData;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "model_debug"})
    @java.lang.Deprecated
    public static final class $serializer implements kotlinx.serialization.internal.GeneratedSerializer<com.kotlin.learn.catalog.core.model.NetworkMovieData> {
        @org.jetbrains.annotations.NotNull
        public static final com.kotlin.learn.catalog.core.model.NetworkMovieData.$serializer INSTANCE = null;
        
        private $serializer() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public kotlinx.serialization.KSerializer<?>[] childSerializers() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public com.kotlin.learn.catalog.core.model.NetworkMovieData deserialize(@org.jetbrains.annotations.NotNull
        kotlinx.serialization.encoding.Decoder decoder) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public kotlinx.serialization.descriptors.SerialDescriptor getDescriptor() {
            return null;
        }
        
        @java.lang.Override
        public void serialize(@org.jetbrains.annotations.NotNull
        kotlinx.serialization.encoding.Encoder encoder, @org.jetbrains.annotations.NotNull
        com.kotlin.learn.catalog.core.model.NetworkMovieData value) {
        }
        
        @org.jetbrains.annotations.NotNull
        public kotlinx.serialization.KSerializer<?>[] typeParametersSerializers() {
            return null;
        }
    }
}