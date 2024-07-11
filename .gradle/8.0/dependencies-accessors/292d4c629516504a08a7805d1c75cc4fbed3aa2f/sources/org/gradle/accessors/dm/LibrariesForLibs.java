package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
*/
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final AccompanistLibraryAccessors laccForAccompanistLibraryAccessors = new AccompanistLibraryAccessors(owner);
    private final AnddridxLibraryAccessors laccForAnddridxLibraryAccessors = new AnddridxLibraryAccessors(owner);
    private final AndroidLibraryAccessors laccForAndroidLibraryAccessors = new AndroidLibraryAccessors(owner);
    private final AndroidxLibraryAccessors laccForAndroidxLibraryAccessors = new AndroidxLibraryAccessors(owner);
    private final ChuckerLibraryAccessors laccForChuckerLibraryAccessors = new ChuckerLibraryAccessors(owner);
    private final CoilLibraryAccessors laccForCoilLibraryAccessors = new CoilLibraryAccessors(owner);
    private final ComLibraryAccessors laccForComLibraryAccessors = new ComLibraryAccessors(owner);
    private final FacebookLibraryAccessors laccForFacebookLibraryAccessors = new FacebookLibraryAccessors(owner);
    private final HiltLibraryAccessors laccForHiltLibraryAccessors = new HiltLibraryAccessors(owner);
    private final IoLibraryAccessors laccForIoLibraryAccessors = new IoLibraryAccessors(owner);
    private final JpLibraryAccessors laccForJpLibraryAccessors = new JpLibraryAccessors(owner);
    private final KotlinLibraryAccessors laccForKotlinLibraryAccessors = new KotlinLibraryAccessors(owner);
    private final KotlinxLibraryAccessors laccForKotlinxLibraryAccessors = new KotlinxLibraryAccessors(owner);
    private final KtorLibraryAccessors laccForKtorLibraryAccessors = new KtorLibraryAccessors(owner);
    private final LintLibraryAccessors laccForLintLibraryAccessors = new LintLibraryAccessors(owner);
    private final PagingLibraryAccessors laccForPagingLibraryAccessors = new PagingLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

        /**
         * Creates a dependency provider for constraintlayout (androidx.constraintlayout:constraintlayout)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getConstraintlayout() { return create("constraintlayout"); }

        /**
         * Creates a dependency provider for gson (com.google.code.gson:gson)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getGson() { return create("gson"); }

        /**
         * Creates a dependency provider for junit (androidx.test.ext:junit)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJunit() { return create("junit"); }

        /**
         * Creates a dependency provider for junit4 (junit:junit)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJunit4() { return create("junit4"); }

        /**
         * Creates a dependency provider for material (com.google.android.material:material)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getMaterial() { return create("material"); }

        /**
         * Creates a dependency provider for timber (com.jakewharton.timber:timber)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTimber() { return create("timber"); }

    /**
     * Returns the group of libraries at accompanist
     */
    public AccompanistLibraryAccessors getAccompanist() { return laccForAccompanistLibraryAccessors; }

    /**
     * Returns the group of libraries at anddridx
     */
    public AnddridxLibraryAccessors getAnddridx() { return laccForAnddridxLibraryAccessors; }

    /**
     * Returns the group of libraries at android
     */
    public AndroidLibraryAccessors getAndroid() { return laccForAndroidLibraryAccessors; }

    /**
     * Returns the group of libraries at androidx
     */
    public AndroidxLibraryAccessors getAndroidx() { return laccForAndroidxLibraryAccessors; }

    /**
     * Returns the group of libraries at chucker
     */
    public ChuckerLibraryAccessors getChucker() { return laccForChuckerLibraryAccessors; }

    /**
     * Returns the group of libraries at coil
     */
    public CoilLibraryAccessors getCoil() { return laccForCoilLibraryAccessors; }

    /**
     * Returns the group of libraries at com
     */
    public ComLibraryAccessors getCom() { return laccForComLibraryAccessors; }

    /**
     * Returns the group of libraries at facebook
     */
    public FacebookLibraryAccessors getFacebook() { return laccForFacebookLibraryAccessors; }

    /**
     * Returns the group of libraries at hilt
     */
    public HiltLibraryAccessors getHilt() { return laccForHiltLibraryAccessors; }

    /**
     * Returns the group of libraries at io
     */
    public IoLibraryAccessors getIo() { return laccForIoLibraryAccessors; }

    /**
     * Returns the group of libraries at jp
     */
    public JpLibraryAccessors getJp() { return laccForJpLibraryAccessors; }

    /**
     * Returns the group of libraries at kotlin
     */
    public KotlinLibraryAccessors getKotlin() { return laccForKotlinLibraryAccessors; }

    /**
     * Returns the group of libraries at kotlinx
     */
    public KotlinxLibraryAccessors getKotlinx() { return laccForKotlinxLibraryAccessors; }

    /**
     * Returns the group of libraries at ktor
     */
    public KtorLibraryAccessors getKtor() { return laccForKtorLibraryAccessors; }

    /**
     * Returns the group of libraries at lint
     */
    public LintLibraryAccessors getLint() { return laccForLintLibraryAccessors; }

    /**
     * Returns the group of libraries at paging
     */
    public PagingLibraryAccessors getPaging() { return laccForPagingLibraryAccessors; }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() { return vaccForVersionAccessors; }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() { return baccForBundleAccessors; }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() { return paccForPluginAccessors; }

    public static class AccompanistLibraryAccessors extends SubDependencyFactory {

        public AccompanistLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for animation (com.google.accompanist:accompanist-navigation-animation)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAnimation() { return create("accompanist.animation"); }

            /**
             * Creates a dependency provider for flowlayout (com.google.accompanist:accompanist-flowlayout)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getFlowlayout() { return create("accompanist.flowlayout"); }

            /**
             * Creates a dependency provider for insets (com.google.accompanist:accompanist-insets)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getInsets() { return create("accompanist.insets"); }

            /**
             * Creates a dependency provider for pager (com.google.accompanist:accompanist-pager)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getPager() { return create("accompanist.pager"); }

            /**
             * Creates a dependency provider for placeholder (com.google.accompanist:accompanist-placeholder-material)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getPlaceholder() { return create("accompanist.placeholder"); }

            /**
             * Creates a dependency provider for swiperefresh (com.google.accompanist:accompanist-swiperefresh)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getSwiperefresh() { return create("accompanist.swiperefresh"); }

            /**
             * Creates a dependency provider for systemuicontroller (com.google.accompanist:accompanist-systemuicontroller)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getSystemuicontroller() { return create("accompanist.systemuicontroller"); }

            /**
             * Creates a dependency provider for web (com.google.accompanist:accompanist-webview)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getWeb() { return create("accompanist.web"); }

    }

    public static class AnddridxLibraryAccessors extends SubDependencyFactory {

        public AnddridxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for webkit (androidx.webkit:webkit)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getWebkit() { return create("anddridx.webkit"); }

    }

    public static class AndroidLibraryAccessors extends SubDependencyFactory {

        public AndroidLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for desugarJdkLibs (com.android.tools:desugar_jdk_libs)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getDesugarJdkLibs() { return create("android.desugarJdkLibs"); }

            /**
             * Creates a dependency provider for gradlePlugin (com.android.tools.build:gradle)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getGradlePlugin() { return create("android.gradlePlugin"); }

    }

    public static class AndroidxLibraryAccessors extends SubDependencyFactory {
        private final AndroidxActivityLibraryAccessors laccForAndroidxActivityLibraryAccessors = new AndroidxActivityLibraryAccessors(owner);
        private final AndroidxAnnotationLibraryAccessors laccForAndroidxAnnotationLibraryAccessors = new AndroidxAnnotationLibraryAccessors(owner);
        private final AndroidxArchLibraryAccessors laccForAndroidxArchLibraryAccessors = new AndroidxArchLibraryAccessors(owner);
        private final AndroidxComposeLibraryAccessors laccForAndroidxComposeLibraryAccessors = new AndroidxComposeLibraryAccessors(owner);
        private final AndroidxCoreLibraryAccessors laccForAndroidxCoreLibraryAccessors = new AndroidxCoreLibraryAccessors(owner);
        private final AndroidxDatabindingLibraryAccessors laccForAndroidxDatabindingLibraryAccessors = new AndroidxDatabindingLibraryAccessors(owner);
        private final AndroidxDatastoreLibraryAccessors laccForAndroidxDatastoreLibraryAccessors = new AndroidxDatastoreLibraryAccessors(owner);
        private final AndroidxHiltLibraryAccessors laccForAndroidxHiltLibraryAccessors = new AndroidxHiltLibraryAccessors(owner);
        private final AndroidxLifecycleLibraryAccessors laccForAndroidxLifecycleLibraryAccessors = new AndroidxLifecycleLibraryAccessors(owner);
        private final AndroidxNavigationLibraryAccessors laccForAndroidxNavigationLibraryAccessors = new AndroidxNavigationLibraryAccessors(owner);
        private final AndroidxSecurityLibraryAccessors laccForAndroidxSecurityLibraryAccessors = new AndroidxSecurityLibraryAccessors(owner);
        private final AndroidxTestLibraryAccessors laccForAndroidxTestLibraryAccessors = new AndroidxTestLibraryAccessors(owner);
        private final AndroidxWorkLibraryAccessors laccForAndroidxWorkLibraryAccessors = new AndroidxWorkLibraryAccessors(owner);

        public AndroidxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for appcompat (androidx.appcompat:appcompat)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAppcompat() { return create("androidx.appcompat"); }

            /**
             * Creates a dependency provider for swiperefreshlayout (androidx.swiperefreshlayout:swiperefreshlayout)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getSwiperefreshlayout() { return create("androidx.swiperefreshlayout"); }

        /**
         * Returns the group of libraries at androidx.activity
         */
        public AndroidxActivityLibraryAccessors getActivity() { return laccForAndroidxActivityLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.annotation
         */
        public AndroidxAnnotationLibraryAccessors getAnnotation() { return laccForAndroidxAnnotationLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.arch
         */
        public AndroidxArchLibraryAccessors getArch() { return laccForAndroidxArchLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.compose
         */
        public AndroidxComposeLibraryAccessors getCompose() { return laccForAndroidxComposeLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.core
         */
        public AndroidxCoreLibraryAccessors getCore() { return laccForAndroidxCoreLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.databinding
         */
        public AndroidxDatabindingLibraryAccessors getDatabinding() { return laccForAndroidxDatabindingLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.datastore
         */
        public AndroidxDatastoreLibraryAccessors getDatastore() { return laccForAndroidxDatastoreLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.hilt
         */
        public AndroidxHiltLibraryAccessors getHilt() { return laccForAndroidxHiltLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.lifecycle
         */
        public AndroidxLifecycleLibraryAccessors getLifecycle() { return laccForAndroidxLifecycleLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.navigation
         */
        public AndroidxNavigationLibraryAccessors getNavigation() { return laccForAndroidxNavigationLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.security
         */
        public AndroidxSecurityLibraryAccessors getSecurity() { return laccForAndroidxSecurityLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.test
         */
        public AndroidxTestLibraryAccessors getTest() { return laccForAndroidxTestLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.work
         */
        public AndroidxWorkLibraryAccessors getWork() { return laccForAndroidxWorkLibraryAccessors; }

    }

    public static class AndroidxActivityLibraryAccessors extends SubDependencyFactory {
        private final AndroidxActivityActivityLibraryAccessors laccForAndroidxActivityActivityLibraryAccessors = new AndroidxActivityActivityLibraryAccessors(owner);
        private final AndroidxActivityFragmentLibraryAccessors laccForAndroidxActivityFragmentLibraryAccessors = new AndroidxActivityFragmentLibraryAccessors(owner);

        public AndroidxActivityLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compose (androidx.activity:activity-compose)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompose() { return create("androidx.activity.compose"); }

        /**
         * Returns the group of libraries at androidx.activity.activity
         */
        public AndroidxActivityActivityLibraryAccessors getActivity() { return laccForAndroidxActivityActivityLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.activity.fragment
         */
        public AndroidxActivityFragmentLibraryAccessors getFragment() { return laccForAndroidxActivityFragmentLibraryAccessors; }

    }

    public static class AndroidxActivityActivityLibraryAccessors extends SubDependencyFactory {

        public AndroidxActivityActivityLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.activity:activity-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.activity.activity.ktx"); }

    }

    public static class AndroidxActivityFragmentLibraryAccessors extends SubDependencyFactory {

        public AndroidxActivityFragmentLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.fragment:fragment-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.activity.fragment.ktx"); }

    }

    public static class AndroidxAnnotationLibraryAccessors extends SubDependencyFactory {

        public AndroidxAnnotationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for jvm (androidx.annotation:annotation-jvm)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJvm() { return create("androidx.annotation.jvm"); }

    }

    public static class AndroidxArchLibraryAccessors extends SubDependencyFactory {
        private final AndroidxArchCoreLibraryAccessors laccForAndroidxArchCoreLibraryAccessors = new AndroidxArchCoreLibraryAccessors(owner);

        public AndroidxArchLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.arch.core
         */
        public AndroidxArchCoreLibraryAccessors getCore() { return laccForAndroidxArchCoreLibraryAccessors; }

    }

    public static class AndroidxArchCoreLibraryAccessors extends SubDependencyFactory {
        private final AndroidxArchCoreCoreLibraryAccessors laccForAndroidxArchCoreCoreLibraryAccessors = new AndroidxArchCoreCoreLibraryAccessors(owner);

        public AndroidxArchCoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.arch.core.core
         */
        public AndroidxArchCoreCoreLibraryAccessors getCore() { return laccForAndroidxArchCoreCoreLibraryAccessors; }

    }

    public static class AndroidxArchCoreCoreLibraryAccessors extends SubDependencyFactory {

        public AndroidxArchCoreCoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for testing (androidx.arch.core:core-testing)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTesting() { return create("androidx.arch.core.core.testing"); }

    }

    public static class AndroidxComposeLibraryAccessors extends SubDependencyFactory {
        private final AndroidxComposeFoundationLibraryAccessors laccForAndroidxComposeFoundationLibraryAccessors = new AndroidxComposeFoundationLibraryAccessors(owner);
        private final AndroidxComposeMaterialLibraryAccessors laccForAndroidxComposeMaterialLibraryAccessors = new AndroidxComposeMaterialLibraryAccessors(owner);
        private final AndroidxComposeMaterial3LibraryAccessors laccForAndroidxComposeMaterial3LibraryAccessors = new AndroidxComposeMaterial3LibraryAccessors(owner);
        private final AndroidxComposeRuntimeLibraryAccessors laccForAndroidxComposeRuntimeLibraryAccessors = new AndroidxComposeRuntimeLibraryAccessors(owner);
        private final AndroidxComposeUiLibraryAccessors laccForAndroidxComposeUiLibraryAccessors = new AndroidxComposeUiLibraryAccessors(owner);

        public AndroidxComposeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for bom (androidx.compose:compose-bom)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getBom() { return create("androidx.compose.bom"); }

        /**
         * Returns the group of libraries at androidx.compose.foundation
         */
        public AndroidxComposeFoundationLibraryAccessors getFoundation() { return laccForAndroidxComposeFoundationLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.compose.material
         */
        public AndroidxComposeMaterialLibraryAccessors getMaterial() { return laccForAndroidxComposeMaterialLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.compose.material3
         */
        public AndroidxComposeMaterial3LibraryAccessors getMaterial3() { return laccForAndroidxComposeMaterial3LibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.compose.runtime
         */
        public AndroidxComposeRuntimeLibraryAccessors getRuntime() { return laccForAndroidxComposeRuntimeLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.compose.ui
         */
        public AndroidxComposeUiLibraryAccessors getUi() { return laccForAndroidxComposeUiLibraryAccessors; }

    }

    public static class AndroidxComposeFoundationLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxComposeFoundationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for foundation (androidx.compose.foundation:foundation)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.compose.foundation"); }

            /**
             * Creates a dependency provider for layout (androidx.compose.foundation:foundation-layout)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getLayout() { return create("androidx.compose.foundation.layout"); }

    }

    public static class AndroidxComposeMaterialLibraryAccessors extends SubDependencyFactory {

        public AndroidxComposeMaterialLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for iconsExtended (androidx.compose.material:material-icons-extended)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getIconsExtended() { return create("androidx.compose.material.iconsExtended"); }

    }

    public static class AndroidxComposeMaterial3LibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxComposeMaterial3LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for material3 (androidx.compose.material3:material3)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.compose.material3"); }

            /**
             * Creates a dependency provider for windowSizeClass (androidx.compose.material3:material3-window-size-class)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getWindowSizeClass() { return create("androidx.compose.material3.windowSizeClass"); }

    }

    public static class AndroidxComposeRuntimeLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxComposeRuntimeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for runtime (androidx.compose.runtime:runtime)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.compose.runtime"); }

            /**
             * Creates a dependency provider for livedata (androidx.compose.runtime:runtime-livedata)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getLivedata() { return create("androidx.compose.runtime.livedata"); }

    }

    public static class AndroidxComposeUiLibraryAccessors extends SubDependencyFactory {
        private final AndroidxComposeUiToolingLibraryAccessors laccForAndroidxComposeUiToolingLibraryAccessors = new AndroidxComposeUiToolingLibraryAccessors(owner);

        public AndroidxComposeUiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for test (androidx.compose.ui:ui-test-junit4)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTest() { return create("androidx.compose.ui.test"); }

            /**
             * Creates a dependency provider for testManifest (androidx.compose.ui:ui-test-manifest)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTestManifest() { return create("androidx.compose.ui.testManifest"); }

            /**
             * Creates a dependency provider for util (androidx.compose.ui:ui-util)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getUtil() { return create("androidx.compose.ui.util"); }

        /**
         * Returns the group of libraries at androidx.compose.ui.tooling
         */
        public AndroidxComposeUiToolingLibraryAccessors getTooling() { return laccForAndroidxComposeUiToolingLibraryAccessors; }

    }

    public static class AndroidxComposeUiToolingLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxComposeUiToolingLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for tooling (androidx.compose.ui:ui-tooling)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.compose.ui.tooling"); }

            /**
             * Creates a dependency provider for preview (androidx.compose.ui:ui-tooling-preview)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getPreview() { return create("androidx.compose.ui.tooling.preview"); }

    }

    public static class AndroidxCoreLibraryAccessors extends SubDependencyFactory {

        public AndroidxCoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.core:core-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.core.ktx"); }

            /**
             * Creates a dependency provider for splashscreen (androidx.core:core-splashscreen)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getSplashscreen() { return create("androidx.core.splashscreen"); }

    }

    public static class AndroidxDatabindingLibraryAccessors extends SubDependencyFactory {

        public AndroidxDatabindingLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for runtime (androidx.databinding:databinding-runtime)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getRuntime() { return create("androidx.databinding.runtime"); }

    }

    public static class AndroidxDatastoreLibraryAccessors extends SubDependencyFactory {
        private final AndroidxDatastoreDatastoreLibraryAccessors laccForAndroidxDatastoreDatastoreLibraryAccessors = new AndroidxDatastoreDatastoreLibraryAccessors(owner);

        public AndroidxDatastoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.datastore.datastore
         */
        public AndroidxDatastoreDatastoreLibraryAccessors getDatastore() { return laccForAndroidxDatastoreDatastoreLibraryAccessors; }

    }

    public static class AndroidxDatastoreDatastoreLibraryAccessors extends SubDependencyFactory {

        public AndroidxDatastoreDatastoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for preferences (androidx.datastore:datastore-preferences)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getPreferences() { return create("androidx.datastore.datastore.preferences"); }

    }

    public static class AndroidxHiltLibraryAccessors extends SubDependencyFactory {
        private final AndroidxHiltNavigationLibraryAccessors laccForAndroidxHiltNavigationLibraryAccessors = new AndroidxHiltNavigationLibraryAccessors(owner);

        public AndroidxHiltLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.hilt.navigation
         */
        public AndroidxHiltNavigationLibraryAccessors getNavigation() { return laccForAndroidxHiltNavigationLibraryAccessors; }

    }

    public static class AndroidxHiltNavigationLibraryAccessors extends SubDependencyFactory {

        public AndroidxHiltNavigationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compose (androidx.hilt:hilt-navigation-compose)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompose() { return create("androidx.hilt.navigation.compose"); }

    }

    public static class AndroidxLifecycleLibraryAccessors extends SubDependencyFactory {
        private final AndroidxLifecycleLifecycleLibraryAccessors laccForAndroidxLifecycleLifecycleLibraryAccessors = new AndroidxLifecycleLifecycleLibraryAccessors(owner);

        public AndroidxLifecycleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for common (androidx.lifecycle:lifecycle-common)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCommon() { return create("androidx.lifecycle.common"); }

            /**
             * Creates a dependency provider for runtimeCompose (androidx.lifecycle:lifecycle-runtime-compose)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getRuntimeCompose() { return create("androidx.lifecycle.runtimeCompose"); }

            /**
             * Creates a dependency provider for viewModelCompose (androidx.lifecycle:lifecycle-viewmodel-compose)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getViewModelCompose() { return create("androidx.lifecycle.viewModelCompose"); }

        /**
         * Returns the group of libraries at androidx.lifecycle.lifecycle
         */
        public AndroidxLifecycleLifecycleLibraryAccessors getLifecycle() { return laccForAndroidxLifecycleLifecycleLibraryAccessors; }

    }

    public static class AndroidxLifecycleLifecycleLibraryAccessors extends SubDependencyFactory {
        private final AndroidxLifecycleLifecycleCommonLibraryAccessors laccForAndroidxLifecycleLifecycleCommonLibraryAccessors = new AndroidxLifecycleLifecycleCommonLibraryAccessors(owner);
        private final AndroidxLifecycleLifecycleLivedataLibraryAccessors laccForAndroidxLifecycleLifecycleLivedataLibraryAccessors = new AndroidxLifecycleLifecycleLivedataLibraryAccessors(owner);
        private final AndroidxLifecycleLifecycleReactivestreamsLibraryAccessors laccForAndroidxLifecycleLifecycleReactivestreamsLibraryAccessors = new AndroidxLifecycleLifecycleReactivestreamsLibraryAccessors(owner);
        private final AndroidxLifecycleLifecycleRuntimeLibraryAccessors laccForAndroidxLifecycleLifecycleRuntimeLibraryAccessors = new AndroidxLifecycleLifecycleRuntimeLibraryAccessors(owner);
        private final AndroidxLifecycleLifecycleViewmodelLibraryAccessors laccForAndroidxLifecycleLifecycleViewmodelLibraryAccessors = new AndroidxLifecycleLifecycleViewmodelLibraryAccessors(owner);

        public AndroidxLifecycleLifecycleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compiler (androidx.lifecycle:lifecycle-compiler)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompiler() { return create("androidx.lifecycle.lifecycle.compiler"); }

            /**
             * Creates a dependency provider for process (androidx.lifecycle:lifecycle-process)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getProcess() { return create("androidx.lifecycle.lifecycle.process"); }

            /**
             * Creates a dependency provider for service (androidx.lifecycle:lifecycle-service)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getService() { return create("androidx.lifecycle.lifecycle.service"); }

        /**
         * Returns the group of libraries at androidx.lifecycle.lifecycle.common
         */
        public AndroidxLifecycleLifecycleCommonLibraryAccessors getCommon() { return laccForAndroidxLifecycleLifecycleCommonLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.lifecycle.lifecycle.livedata
         */
        public AndroidxLifecycleLifecycleLivedataLibraryAccessors getLivedata() { return laccForAndroidxLifecycleLifecycleLivedataLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.lifecycle.lifecycle.reactivestreams
         */
        public AndroidxLifecycleLifecycleReactivestreamsLibraryAccessors getReactivestreams() { return laccForAndroidxLifecycleLifecycleReactivestreamsLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.lifecycle.lifecycle.runtime
         */
        public AndroidxLifecycleLifecycleRuntimeLibraryAccessors getRuntime() { return laccForAndroidxLifecycleLifecycleRuntimeLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.lifecycle.lifecycle.viewmodel
         */
        public AndroidxLifecycleLifecycleViewmodelLibraryAccessors getViewmodel() { return laccForAndroidxLifecycleLifecycleViewmodelLibraryAccessors; }

    }

    public static class AndroidxLifecycleLifecycleCommonLibraryAccessors extends SubDependencyFactory {

        public AndroidxLifecycleLifecycleCommonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for java8 (androidx.lifecycle:lifecycle-common-java8)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJava8() { return create("androidx.lifecycle.lifecycle.common.java8"); }

    }

    public static class AndroidxLifecycleLifecycleLivedataLibraryAccessors extends SubDependencyFactory {

        public AndroidxLifecycleLifecycleLivedataLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.lifecycle:lifecycle-livedata-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.lifecycle.lifecycle.livedata.ktx"); }

    }

    public static class AndroidxLifecycleLifecycleReactivestreamsLibraryAccessors extends SubDependencyFactory {

        public AndroidxLifecycleLifecycleReactivestreamsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.lifecycle:lifecycle-reactivestreams-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.lifecycle.lifecycle.reactivestreams.ktx"); }

    }

    public static class AndroidxLifecycleLifecycleRuntimeLibraryAccessors extends SubDependencyFactory {

        public AndroidxLifecycleLifecycleRuntimeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.lifecycle:lifecycle-runtime-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.lifecycle.lifecycle.runtime.ktx"); }

            /**
             * Creates a dependency provider for testing (androidx.lifecycle:lifecycle-runtime-testing)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTesting() { return create("androidx.lifecycle.lifecycle.runtime.testing"); }

    }

    public static class AndroidxLifecycleLifecycleViewmodelLibraryAccessors extends SubDependencyFactory {

        public AndroidxLifecycleLifecycleViewmodelLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for savedstate (androidx.lifecycle:lifecycle-viewmodel-savedstate)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getSavedstate() { return create("androidx.lifecycle.lifecycle.viewmodel.savedstate"); }

    }

    public static class AndroidxNavigationLibraryAccessors extends SubDependencyFactory {
        private final AndroidxNavigationFragmentLibraryAccessors laccForAndroidxNavigationFragmentLibraryAccessors = new AndroidxNavigationFragmentLibraryAccessors(owner);
        private final AndroidxNavigationRuntimeLibraryAccessors laccForAndroidxNavigationRuntimeLibraryAccessors = new AndroidxNavigationRuntimeLibraryAccessors(owner);
        private final AndroidxNavigationUiLibraryAccessors laccForAndroidxNavigationUiLibraryAccessors = new AndroidxNavigationUiLibraryAccessors(owner);

        public AndroidxNavigationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compose (androidx.navigation:navigation-compose)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompose() { return create("androidx.navigation.compose"); }

            /**
             * Creates a dependency provider for testing (androidx.navigation:navigation-testing)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTesting() { return create("androidx.navigation.testing"); }

        /**
         * Returns the group of libraries at androidx.navigation.fragment
         */
        public AndroidxNavigationFragmentLibraryAccessors getFragment() { return laccForAndroidxNavigationFragmentLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.navigation.runtime
         */
        public AndroidxNavigationRuntimeLibraryAccessors getRuntime() { return laccForAndroidxNavigationRuntimeLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.navigation.ui
         */
        public AndroidxNavigationUiLibraryAccessors getUi() { return laccForAndroidxNavigationUiLibraryAccessors; }

    }

    public static class AndroidxNavigationFragmentLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxNavigationFragmentLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for fragment (androidx.navigation:navigation-fragment)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.navigation.fragment"); }

            /**
             * Creates a dependency provider for ktx (androidx.navigation:navigation-fragment-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.navigation.fragment.ktx"); }

    }

    public static class AndroidxNavigationRuntimeLibraryAccessors extends SubDependencyFactory {

        public AndroidxNavigationRuntimeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.navigation:navigation-runtime-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.navigation.runtime.ktx"); }

    }

    public static class AndroidxNavigationUiLibraryAccessors extends SubDependencyFactory {

        public AndroidxNavigationUiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.navigation:navigation-ui-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.navigation.ui.ktx"); }

    }

    public static class AndroidxSecurityLibraryAccessors extends SubDependencyFactory {
        private final AndroidxSecuritySecurityLibraryAccessors laccForAndroidxSecuritySecurityLibraryAccessors = new AndroidxSecuritySecurityLibraryAccessors(owner);

        public AndroidxSecurityLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.security.security
         */
        public AndroidxSecuritySecurityLibraryAccessors getSecurity() { return laccForAndroidxSecuritySecurityLibraryAccessors; }

    }

    public static class AndroidxSecuritySecurityLibraryAccessors extends SubDependencyFactory {

        public AndroidxSecuritySecurityLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for crypto (androidx.security:security-crypto)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCrypto() { return create("androidx.security.security.crypto"); }

    }

    public static class AndroidxTestLibraryAccessors extends SubDependencyFactory {
        private final AndroidxTestCoreLibraryAccessors laccForAndroidxTestCoreLibraryAccessors = new AndroidxTestCoreLibraryAccessors(owner);
        private final AndroidxTestEspressoLibraryAccessors laccForAndroidxTestEspressoLibraryAccessors = new AndroidxTestEspressoLibraryAccessors(owner);

        public AndroidxTestLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ext (androidx.test.ext:junit-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getExt() { return create("androidx.test.ext"); }

            /**
             * Creates a dependency provider for rules (androidx.test:rules)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getRules() { return create("androidx.test.rules"); }

            /**
             * Creates a dependency provider for runner (androidx.test:runner)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getRunner() { return create("androidx.test.runner"); }

        /**
         * Returns the group of libraries at androidx.test.core
         */
        public AndroidxTestCoreLibraryAccessors getCore() { return laccForAndroidxTestCoreLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.test.espresso
         */
        public AndroidxTestEspressoLibraryAccessors getEspresso() { return laccForAndroidxTestEspressoLibraryAccessors; }

    }

    public static class AndroidxTestCoreLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxTestCoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (androidx.test:core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.test.core"); }

            /**
             * Creates a dependency provider for ktx (androidx.test:core-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.test.core.ktx"); }

    }

    public static class AndroidxTestEspressoLibraryAccessors extends SubDependencyFactory {

        public AndroidxTestEspressoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (androidx.test.espresso:espresso-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("androidx.test.espresso.core"); }

    }

    public static class AndroidxWorkLibraryAccessors extends SubDependencyFactory {
        private final AndroidxWorkRuntimeLibraryAccessors laccForAndroidxWorkRuntimeLibraryAccessors = new AndroidxWorkRuntimeLibraryAccessors(owner);

        public AndroidxWorkLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.work.runtime
         */
        public AndroidxWorkRuntimeLibraryAccessors getRuntime() { return laccForAndroidxWorkRuntimeLibraryAccessors; }

    }

    public static class AndroidxWorkRuntimeLibraryAccessors extends SubDependencyFactory {

        public AndroidxWorkRuntimeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.work:work-runtime-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.work.runtime.ktx"); }

    }

    public static class ChuckerLibraryAccessors extends SubDependencyFactory {

        public ChuckerLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for debug (com.github.chuckerteam.chucker:library)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getDebug() { return create("chucker.debug"); }

            /**
             * Creates a dependency provider for release (com.github.chuckerteam.chucker:library-no-op)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getRelease() { return create("chucker.release"); }

    }

    public static class CoilLibraryAccessors extends SubDependencyFactory {
        private final CoilKtLibraryAccessors laccForCoilKtLibraryAccessors = new CoilKtLibraryAccessors(owner);

        public CoilLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at coil.kt
         */
        public CoilKtLibraryAccessors getKt() { return laccForCoilKtLibraryAccessors; }

    }

    public static class CoilKtLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public CoilKtLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for kt (io.coil-kt:coil)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("coil.kt"); }

            /**
             * Creates a dependency provider for compose (io.coil-kt:coil-compose)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompose() { return create("coil.kt.compose"); }

            /**
             * Creates a dependency provider for svg (io.coil-kt:coil-svg)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getSvg() { return create("coil.kt.svg"); }

    }

    public static class ComLibraryAccessors extends SubDependencyFactory {
        private final ComAndroidLibraryAccessors laccForComAndroidLibraryAccessors = new ComAndroidLibraryAccessors(owner);
        private final ComGithubLibraryAccessors laccForComGithubLibraryAccessors = new ComGithubLibraryAccessors(owner);
        private final ComGoogleLibraryAccessors laccForComGoogleLibraryAccessors = new ComGoogleLibraryAccessors(owner);
        private final ComI18nextLibraryAccessors laccForComI18nextLibraryAccessors = new ComI18nextLibraryAccessors(owner);
        private final ComJakewhartonLibraryAccessors laccForComJakewhartonLibraryAccessors = new ComJakewhartonLibraryAccessors(owner);
        private final ComPierfrancescosoffrittiLibraryAccessors laccForComPierfrancescosoffrittiLibraryAccessors = new ComPierfrancescosoffrittiLibraryAccessors(owner);
        private final ComSquareupLibraryAccessors laccForComSquareupLibraryAccessors = new ComSquareupLibraryAccessors(owner);

        public ComLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.android
         */
        public ComAndroidLibraryAccessors getAndroid() { return laccForComAndroidLibraryAccessors; }

        /**
         * Returns the group of libraries at com.github
         */
        public ComGithubLibraryAccessors getGithub() { return laccForComGithubLibraryAccessors; }

        /**
         * Returns the group of libraries at com.google
         */
        public ComGoogleLibraryAccessors getGoogle() { return laccForComGoogleLibraryAccessors; }

        /**
         * Returns the group of libraries at com.i18next
         */
        public ComI18nextLibraryAccessors getI18next() { return laccForComI18nextLibraryAccessors; }

        /**
         * Returns the group of libraries at com.jakewharton
         */
        public ComJakewhartonLibraryAccessors getJakewharton() { return laccForComJakewhartonLibraryAccessors; }

        /**
         * Returns the group of libraries at com.pierfrancescosoffritti
         */
        public ComPierfrancescosoffrittiLibraryAccessors getPierfrancescosoffritti() { return laccForComPierfrancescosoffrittiLibraryAccessors; }

        /**
         * Returns the group of libraries at com.squareup
         */
        public ComSquareupLibraryAccessors getSquareup() { return laccForComSquareupLibraryAccessors; }

    }

    public static class ComAndroidLibraryAccessors extends SubDependencyFactory {
        private final ComAndroidSupportLibraryAccessors laccForComAndroidSupportLibraryAccessors = new ComAndroidSupportLibraryAccessors(owner);

        public ComAndroidLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.android.support
         */
        public ComAndroidSupportLibraryAccessors getSupport() { return laccForComAndroidSupportLibraryAccessors; }

    }

    public static class ComAndroidSupportLibraryAccessors extends SubDependencyFactory {

        public ComAndroidSupportLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for multidex (com.android.support:multidex)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getMultidex() { return create("com.android.support.multidex"); }

    }

    public static class ComGithubLibraryAccessors extends SubDependencyFactory {
        private final ComGithubBumptechLibraryAccessors laccForComGithubBumptechLibraryAccessors = new ComGithubBumptechLibraryAccessors(owner);
        private final ComGithubZhpanvipLibraryAccessors laccForComGithubZhpanvipLibraryAccessors = new ComGithubZhpanvipLibraryAccessors(owner);

        public ComGithubLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.github.bumptech
         */
        public ComGithubBumptechLibraryAccessors getBumptech() { return laccForComGithubBumptechLibraryAccessors; }

        /**
         * Returns the group of libraries at com.github.zhpanvip
         */
        public ComGithubZhpanvipLibraryAccessors getZhpanvip() { return laccForComGithubZhpanvipLibraryAccessors; }

    }

    public static class ComGithubBumptechLibraryAccessors extends SubDependencyFactory {

        public ComGithubBumptechLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for glide (com.github.bumptech.glide:glide)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getGlide() { return create("com.github.bumptech.glide"); }

    }

    public static class ComGithubZhpanvipLibraryAccessors extends SubDependencyFactory {

        public ComGithubZhpanvipLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for bannerviewpager (com.github.zhpanvip:bannerviewpager)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getBannerviewpager() { return create("com.github.zhpanvip.bannerviewpager"); }

    }

    public static class ComGoogleLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleAndroidLibraryAccessors laccForComGoogleAndroidLibraryAccessors = new ComGoogleAndroidLibraryAccessors(owner);
        private final ComGoogleFirebaseLibraryAccessors laccForComGoogleFirebaseLibraryAccessors = new ComGoogleFirebaseLibraryAccessors(owner);

        public ComGoogleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.google.android
         */
        public ComGoogleAndroidLibraryAccessors getAndroid() { return laccForComGoogleAndroidLibraryAccessors; }

        /**
         * Returns the group of libraries at com.google.firebase
         */
        public ComGoogleFirebaseLibraryAccessors getFirebase() { return laccForComGoogleFirebaseLibraryAccessors; }

    }

    public static class ComGoogleAndroidLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleAndroidGmsLibraryAccessors laccForComGoogleAndroidGmsLibraryAccessors = new ComGoogleAndroidGmsLibraryAccessors(owner);

        public ComGoogleAndroidLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.google.android.gms
         */
        public ComGoogleAndroidGmsLibraryAccessors getGms() { return laccForComGoogleAndroidGmsLibraryAccessors; }

    }

    public static class ComGoogleAndroidGmsLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleAndroidGmsPlayLibraryAccessors laccForComGoogleAndroidGmsPlayLibraryAccessors = new ComGoogleAndroidGmsPlayLibraryAccessors(owner);

        public ComGoogleAndroidGmsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.google.android.gms.play
         */
        public ComGoogleAndroidGmsPlayLibraryAccessors getPlay() { return laccForComGoogleAndroidGmsPlayLibraryAccessors; }

    }

    public static class ComGoogleAndroidGmsPlayLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleAndroidGmsPlayServicesLibraryAccessors laccForComGoogleAndroidGmsPlayServicesLibraryAccessors = new ComGoogleAndroidGmsPlayServicesLibraryAccessors(owner);

        public ComGoogleAndroidGmsPlayLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.google.android.gms.play.services
         */
        public ComGoogleAndroidGmsPlayServicesLibraryAccessors getServices() { return laccForComGoogleAndroidGmsPlayServicesLibraryAccessors; }

    }

    public static class ComGoogleAndroidGmsPlayServicesLibraryAccessors extends SubDependencyFactory {

        public ComGoogleAndroidGmsPlayServicesLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for auth (com.google.android.gms:play-services-auth)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAuth() { return create("com.google.android.gms.play.services.auth"); }

            /**
             * Creates a dependency provider for location (com.google.android.gms:play-services-location)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getLocation() { return create("com.google.android.gms.play.services.location"); }

    }

    public static class ComGoogleFirebaseLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleFirebaseFirebaseLibraryAccessors laccForComGoogleFirebaseFirebaseLibraryAccessors = new ComGoogleFirebaseFirebaseLibraryAccessors(owner);

        public ComGoogleFirebaseLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.google.firebase.firebase
         */
        public ComGoogleFirebaseFirebaseLibraryAccessors getFirebase() { return laccForComGoogleFirebaseFirebaseLibraryAccessors; }

    }

    public static class ComGoogleFirebaseFirebaseLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleFirebaseFirebaseCommonLibraryAccessors laccForComGoogleFirebaseFirebaseCommonLibraryAccessors = new ComGoogleFirebaseFirebaseCommonLibraryAccessors(owner);
        private final ComGoogleFirebaseFirebaseDatabaseLibraryAccessors laccForComGoogleFirebaseFirebaseDatabaseLibraryAccessors = new ComGoogleFirebaseFirebaseDatabaseLibraryAccessors(owner);
        private final ComGoogleFirebaseFirebaseFirestoreLibraryAccessors laccForComGoogleFirebaseFirebaseFirestoreLibraryAccessors = new ComGoogleFirebaseFirebaseFirestoreLibraryAccessors(owner);
        private final ComGoogleFirebaseFirebaseMessagingLibraryAccessors laccForComGoogleFirebaseFirebaseMessagingLibraryAccessors = new ComGoogleFirebaseFirebaseMessagingLibraryAccessors(owner);

        public ComGoogleFirebaseFirebaseLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for auth (com.google.firebase:firebase-auth)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAuth() { return create("com.google.firebase.firebase.auth"); }

        /**
         * Returns the group of libraries at com.google.firebase.firebase.common
         */
        public ComGoogleFirebaseFirebaseCommonLibraryAccessors getCommon() { return laccForComGoogleFirebaseFirebaseCommonLibraryAccessors; }

        /**
         * Returns the group of libraries at com.google.firebase.firebase.database
         */
        public ComGoogleFirebaseFirebaseDatabaseLibraryAccessors getDatabase() { return laccForComGoogleFirebaseFirebaseDatabaseLibraryAccessors; }

        /**
         * Returns the group of libraries at com.google.firebase.firebase.firestore
         */
        public ComGoogleFirebaseFirebaseFirestoreLibraryAccessors getFirestore() { return laccForComGoogleFirebaseFirebaseFirestoreLibraryAccessors; }

        /**
         * Returns the group of libraries at com.google.firebase.firebase.messaging
         */
        public ComGoogleFirebaseFirebaseMessagingLibraryAccessors getMessaging() { return laccForComGoogleFirebaseFirebaseMessagingLibraryAccessors; }

    }

    public static class ComGoogleFirebaseFirebaseCommonLibraryAccessors extends SubDependencyFactory {

        public ComGoogleFirebaseFirebaseCommonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (com.google.firebase:firebase-common-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("com.google.firebase.firebase.common.ktx"); }

    }

    public static class ComGoogleFirebaseFirebaseDatabaseLibraryAccessors extends SubDependencyFactory {

        public ComGoogleFirebaseFirebaseDatabaseLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (com.google.firebase:firebase-database-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("com.google.firebase.firebase.database.ktx"); }

    }

    public static class ComGoogleFirebaseFirebaseFirestoreLibraryAccessors extends SubDependencyFactory {

        public ComGoogleFirebaseFirebaseFirestoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (com.google.firebase:firebase-firestore-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("com.google.firebase.firebase.firestore.ktx"); }

    }

    public static class ComGoogleFirebaseFirebaseMessagingLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public ComGoogleFirebaseFirebaseMessagingLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for messaging (com.google.firebase:firebase-messaging)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("com.google.firebase.firebase.messaging"); }

            /**
             * Creates a dependency provider for ktx (com.google.firebase:firebase-messaging-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("com.google.firebase.firebase.messaging.ktx"); }

    }

    public static class ComI18nextLibraryAccessors extends SubDependencyFactory {
        private final ComI18nextI18nextLibraryAccessors laccForComI18nextI18nextLibraryAccessors = new ComI18nextI18nextLibraryAccessors(owner);

        public ComI18nextLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.i18next.i18next
         */
        public ComI18nextI18nextLibraryAccessors getI18next() { return laccForComI18nextI18nextLibraryAccessors; }

    }

    public static class ComI18nextI18nextLibraryAccessors extends SubDependencyFactory {

        public ComI18nextI18nextLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for android (com.i18next:i18next-android)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAndroid() { return create("com.i18next.i18next.android"); }

    }

    public static class ComJakewhartonLibraryAccessors extends SubDependencyFactory {
        private final ComJakewhartonThreetenabpLibraryAccessors laccForComJakewhartonThreetenabpLibraryAccessors = new ComJakewhartonThreetenabpLibraryAccessors(owner);

        public ComJakewhartonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.jakewharton.threetenabp
         */
        public ComJakewhartonThreetenabpLibraryAccessors getThreetenabp() { return laccForComJakewhartonThreetenabpLibraryAccessors; }

    }

    public static class ComJakewhartonThreetenabpLibraryAccessors extends SubDependencyFactory {

        public ComJakewhartonThreetenabpLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for threetenabp (com.jakewharton.threetenabp:threetenabp)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getThreetenabp() { return create("com.jakewharton.threetenabp.threetenabp"); }

    }

    public static class ComPierfrancescosoffrittiLibraryAccessors extends SubDependencyFactory {
        private final ComPierfrancescosoffrittiAndroidyoutubeplayerLibraryAccessors laccForComPierfrancescosoffrittiAndroidyoutubeplayerLibraryAccessors = new ComPierfrancescosoffrittiAndroidyoutubeplayerLibraryAccessors(owner);

        public ComPierfrancescosoffrittiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.pierfrancescosoffritti.androidyoutubeplayer
         */
        public ComPierfrancescosoffrittiAndroidyoutubeplayerLibraryAccessors getAndroidyoutubeplayer() { return laccForComPierfrancescosoffrittiAndroidyoutubeplayerLibraryAccessors; }

    }

    public static class ComPierfrancescosoffrittiAndroidyoutubeplayerLibraryAccessors extends SubDependencyFactory {

        public ComPierfrancescosoffrittiAndroidyoutubeplayerLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (com.pierfrancescosoffritti.androidyoutubeplayer:core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("com.pierfrancescosoffritti.androidyoutubeplayer.core"); }

    }

    public static class ComSquareupLibraryAccessors extends SubDependencyFactory {
        private final ComSquareupMoshiLibraryAccessors laccForComSquareupMoshiLibraryAccessors = new ComSquareupMoshiLibraryAccessors(owner);

        public ComSquareupLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.squareup.moshi
         */
        public ComSquareupMoshiLibraryAccessors getMoshi() { return laccForComSquareupMoshiLibraryAccessors; }

    }

    public static class ComSquareupMoshiLibraryAccessors extends SubDependencyFactory {
        private final ComSquareupMoshiMoshiLibraryAccessors laccForComSquareupMoshiMoshiLibraryAccessors = new ComSquareupMoshiMoshiLibraryAccessors(owner);

        public ComSquareupMoshiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.squareup.moshi.moshi
         */
        public ComSquareupMoshiMoshiLibraryAccessors getMoshi() { return laccForComSquareupMoshiMoshiLibraryAccessors; }

    }

    public static class ComSquareupMoshiMoshiLibraryAccessors extends SubDependencyFactory {
        private final ComSquareupMoshiMoshiKotlinLibraryAccessors laccForComSquareupMoshiMoshiKotlinLibraryAccessors = new ComSquareupMoshiMoshiKotlinLibraryAccessors(owner);

        public ComSquareupMoshiMoshiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.squareup.moshi.moshi.kotlin
         */
        public ComSquareupMoshiMoshiKotlinLibraryAccessors getKotlin() { return laccForComSquareupMoshiMoshiKotlinLibraryAccessors; }

    }

    public static class ComSquareupMoshiMoshiKotlinLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public ComSquareupMoshiMoshiKotlinLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for kotlin (com.squareup.moshi:moshi-kotlin)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("com.squareup.moshi.moshi.kotlin"); }

            /**
             * Creates a dependency provider for codegen (com.squareup.moshi:moshi-kotlin-codegen)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCodegen() { return create("com.squareup.moshi.moshi.kotlin.codegen"); }

    }

    public static class FacebookLibraryAccessors extends SubDependencyFactory {

        public FacebookLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for login (com.facebook.android:facebook-login)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getLogin() { return create("facebook.login"); }

            /**
             * Creates a dependency provider for shimmer (com.facebook.shimmer:shimmer)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getShimmer() { return create("facebook.shimmer"); }

    }

    public static class HiltLibraryAccessors extends SubDependencyFactory {
        private final HiltAndroidLibraryAccessors laccForHiltAndroidLibraryAccessors = new HiltAndroidLibraryAccessors(owner);
        private final HiltExtLibraryAccessors laccForHiltExtLibraryAccessors = new HiltExtLibraryAccessors(owner);

        public HiltLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compiler (com.google.dagger:hilt-android-compiler)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompiler() { return create("hilt.compiler"); }

        /**
         * Returns the group of libraries at hilt.android
         */
        public HiltAndroidLibraryAccessors getAndroid() { return laccForHiltAndroidLibraryAccessors; }

        /**
         * Returns the group of libraries at hilt.ext
         */
        public HiltExtLibraryAccessors getExt() { return laccForHiltExtLibraryAccessors; }

    }

    public static class HiltAndroidLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public HiltAndroidLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for android (com.google.dagger:hilt-android)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("hilt.android"); }

            /**
             * Creates a dependency provider for testing (com.google.dagger:hilt-android-testing)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTesting() { return create("hilt.android.testing"); }

    }

    public static class HiltExtLibraryAccessors extends SubDependencyFactory {

        public HiltExtLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compiler (androidx.hilt:hilt-compiler)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompiler() { return create("hilt.ext.compiler"); }

            /**
             * Creates a dependency provider for work (androidx.hilt:hilt-work)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getWork() { return create("hilt.ext.work"); }

    }

    public static class IoLibraryAccessors extends SubDependencyFactory {
        private final IoGithubLibraryAccessors laccForIoGithubLibraryAccessors = new IoGithubLibraryAccessors(owner);

        public IoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at io.github
         */
        public IoGithubLibraryAccessors getGithub() { return laccForIoGithubLibraryAccessors; }

    }

    public static class IoGithubLibraryAccessors extends SubDependencyFactory {
        private final IoGithubInflationxLibraryAccessors laccForIoGithubInflationxLibraryAccessors = new IoGithubInflationxLibraryAccessors(owner);

        public IoGithubLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at io.github.inflationx
         */
        public IoGithubInflationxLibraryAccessors getInflationx() { return laccForIoGithubInflationxLibraryAccessors; }

    }

    public static class IoGithubInflationxLibraryAccessors extends SubDependencyFactory {

        public IoGithubInflationxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for viewpump (io.github.inflationx:viewpump)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getViewpump() { return create("io.github.inflationx.viewpump"); }

    }

    public static class JpLibraryAccessors extends SubDependencyFactory {
        private final JpWasabeefLibraryAccessors laccForJpWasabeefLibraryAccessors = new JpWasabeefLibraryAccessors(owner);

        public JpLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at jp.wasabeef
         */
        public JpWasabeefLibraryAccessors getWasabeef() { return laccForJpWasabeefLibraryAccessors; }

    }

    public static class JpWasabeefLibraryAccessors extends SubDependencyFactory {
        private final JpWasabeefGlideLibraryAccessors laccForJpWasabeefGlideLibraryAccessors = new JpWasabeefGlideLibraryAccessors(owner);

        public JpWasabeefLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for blurry (jp.wasabeef:blurry)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getBlurry() { return create("jp.wasabeef.blurry"); }

        /**
         * Returns the group of libraries at jp.wasabeef.glide
         */
        public JpWasabeefGlideLibraryAccessors getGlide() { return laccForJpWasabeefGlideLibraryAccessors; }

    }

    public static class JpWasabeefGlideLibraryAccessors extends SubDependencyFactory {

        public JpWasabeefGlideLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for transformations (jp.wasabeef:glide-transformations)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTransformations() { return create("jp.wasabeef.glide.transformations"); }

    }

    public static class KotlinLibraryAccessors extends SubDependencyFactory {

        public KotlinLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for gradlePlugin (org.jetbrains.kotlin:kotlin-gradle-plugin)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getGradlePlugin() { return create("kotlin.gradlePlugin"); }

            /**
             * Creates a dependency provider for stdlib (org.jetbrains.kotlin:kotlin-stdlib-jdk8)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getStdlib() { return create("kotlin.stdlib"); }

    }

    public static class KotlinxLibraryAccessors extends SubDependencyFactory {
        private final KotlinxCoroutinesLibraryAccessors laccForKotlinxCoroutinesLibraryAccessors = new KotlinxCoroutinesLibraryAccessors(owner);
        private final KotlinxSerializationLibraryAccessors laccForKotlinxSerializationLibraryAccessors = new KotlinxSerializationLibraryAccessors(owner);

        public KotlinxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for datetime (org.jetbrains.kotlinx:kotlinx-datetime)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getDatetime() { return create("kotlinx.datetime"); }

        /**
         * Returns the group of libraries at kotlinx.coroutines
         */
        public KotlinxCoroutinesLibraryAccessors getCoroutines() { return laccForKotlinxCoroutinesLibraryAccessors; }

        /**
         * Returns the group of libraries at kotlinx.serialization
         */
        public KotlinxSerializationLibraryAccessors getSerialization() { return laccForKotlinxSerializationLibraryAccessors; }

    }

    public static class KotlinxCoroutinesLibraryAccessors extends SubDependencyFactory {

        public KotlinxCoroutinesLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for android (org.jetbrains.kotlinx:kotlinx-coroutines-android)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAndroid() { return create("kotlinx.coroutines.android"); }

            /**
             * Creates a dependency provider for test (org.jetbrains.kotlinx:kotlinx-coroutines-test)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTest() { return create("kotlinx.coroutines.test"); }

    }

    public static class KotlinxSerializationLibraryAccessors extends SubDependencyFactory {

        public KotlinxSerializationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for json (org.jetbrains.kotlinx:kotlinx-serialization-json)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJson() { return create("kotlinx.serialization.json"); }

    }

    public static class KtorLibraryAccessors extends SubDependencyFactory {
        private final KtorContentLibraryAccessors laccForKtorContentLibraryAccessors = new KtorContentLibraryAccessors(owner);
        private final KtorSerializationLibraryAccessors laccForKtorSerializationLibraryAccessors = new KtorSerializationLibraryAccessors(owner);

        public KtorLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (io.ktor:ktor-client-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("ktor.core"); }

            /**
             * Creates a dependency provider for logging (io.ktor:ktor-client-logging)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getLogging() { return create("ktor.logging"); }

            /**
             * Creates a dependency provider for okhttp (io.ktor:ktor-client-okhttp)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getOkhttp() { return create("ktor.okhttp"); }

            /**
             * Creates a dependency provider for resources (io.ktor:ktor-client-resources)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getResources() { return create("ktor.resources"); }

        /**
         * Returns the group of libraries at ktor.content
         */
        public KtorContentLibraryAccessors getContent() { return laccForKtorContentLibraryAccessors; }

        /**
         * Returns the group of libraries at ktor.serialization
         */
        public KtorSerializationLibraryAccessors getSerialization() { return laccForKtorSerializationLibraryAccessors; }

    }

    public static class KtorContentLibraryAccessors extends SubDependencyFactory {

        public KtorContentLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for negotiation (io.ktor:ktor-client-content-negotiation)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getNegotiation() { return create("ktor.content.negotiation"); }

    }

    public static class KtorSerializationLibraryAccessors extends SubDependencyFactory {

        public KtorSerializationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for auth (io.ktor:ktor-client-auth)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAuth() { return create("ktor.serialization.auth"); }

            /**
             * Creates a dependency provider for json (io.ktor:ktor-serialization-kotlinx-json)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJson() { return create("ktor.serialization.json"); }

    }

    public static class LintLibraryAccessors extends SubDependencyFactory {

        public LintLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for api (com.android.tools.lint:lint-api)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getApi() { return create("lint.api"); }

    }

    public static class PagingLibraryAccessors extends SubDependencyFactory {

        public PagingLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compose (androidx.paging:paging-compose)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompose() { return create("paging.compose"); }

            /**
             * Creates a dependency provider for runtime (androidx.paging:paging-runtime-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getRuntime() { return create("paging.runtime"); }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final OrgVersionAccessors vaccForOrgVersionAccessors = new OrgVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: accompanist (0.29.2-rc)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAccompanist() { return getVersion("accompanist"); }

            /**
             * Returns the version associated to this alias: androidDesugarJdkLibs (2.0.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidDesugarJdkLibs() { return getVersion("androidDesugarJdkLibs"); }

            /**
             * Returns the version associated to this alias: androidGradlePlugin (8.0.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidGradlePlugin() { return getVersion("androidGradlePlugin"); }

            /**
             * Returns the version associated to this alias: androidxActivity (1.7.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxActivity() { return getVersion("androidxActivity"); }

            /**
             * Returns the version associated to this alias: androidxAppCompat (1.6.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxAppCompat() { return getVersion("androidxAppCompat"); }

            /**
             * Returns the version associated to this alias: androidxComposeBom (2023.06.00)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxComposeBom() { return getVersion("androidxComposeBom"); }

            /**
             * Returns the version associated to this alias: androidxComposeCompiler (1.4.4)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxComposeCompiler() { return getVersion("androidxComposeCompiler"); }

            /**
             * Returns the version associated to this alias: androidxComposeMaterial3 (1.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxComposeMaterial3() { return getVersion("androidxComposeMaterial3"); }

            /**
             * Returns the version associated to this alias: androidxCore (1.10.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxCore() { return getVersion("androidxCore"); }

            /**
             * Returns the version associated to this alias: androidxCoreSplashscreen (1.0.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxCoreSplashscreen() { return getVersion("androidxCoreSplashscreen"); }

            /**
             * Returns the version associated to this alias: androidxEspresso (3.5.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxEspresso() { return getVersion("androidxEspresso"); }

            /**
             * Returns the version associated to this alias: androidxFragment (1.5.7)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxFragment() { return getVersion("androidxFragment"); }

            /**
             * Returns the version associated to this alias: androidxHiltNavigationCompose (1.0.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxHiltNavigationCompose() { return getVersion("androidxHiltNavigationCompose"); }

            /**
             * Returns the version associated to this alias: androidxLifecycle (2.6.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxLifecycle() { return getVersion("androidxLifecycle"); }

            /**
             * Returns the version associated to this alias: androidxNavigation (2.6.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxNavigation() { return getVersion("androidxNavigation"); }

            /**
             * Returns the version associated to this alias: androidxTestCore (1.5.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxTestCore() { return getVersion("androidxTestCore"); }

            /**
             * Returns the version associated to this alias: androidxTestCoreKtx (1.5.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxTestCoreKtx() { return getVersion("androidxTestCoreKtx"); }

            /**
             * Returns the version associated to this alias: androidxTestExt (1.1.5)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxTestExt() { return getVersion("androidxTestExt"); }

            /**
             * Returns the version associated to this alias: androidxTestRules (1.5.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxTestRules() { return getVersion("androidxTestRules"); }

            /**
             * Returns the version associated to this alias: androidxTestRunner (1.5.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxTestRunner() { return getVersion("androidxTestRunner"); }

            /**
             * Returns the version associated to this alias: androidxWebkit (1.7.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxWebkit() { return getVersion("androidxWebkit"); }

            /**
             * Returns the version associated to this alias: androidyoutubeplayerCore (12.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidyoutubeplayerCore() { return getVersion("androidyoutubeplayerCore"); }

            /**
             * Returns the version associated to this alias: annotationJvm (1.6.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAnnotationJvm() { return getVersion("annotationJvm"); }

            /**
             * Returns the version associated to this alias: arch (2.2.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getArch() { return getVersion("arch"); }

            /**
             * Returns the version associated to this alias: bannerViewPager (3.5.11)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getBannerViewPager() { return getVersion("bannerViewPager"); }

            /**
             * Returns the version associated to this alias: blurry (4.0.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getBlurry() { return getVersion("blurry"); }

            /**
             * Returns the version associated to this alias: chucker (3.5.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getChucker() { return getVersion("chucker"); }

            /**
             * Returns the version associated to this alias: coil (2.3.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCoil() { return getVersion("coil"); }

            /**
             * Returns the version associated to this alias: constraintlayout (2.1.4)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getConstraintlayout() { return getVersion("constraintlayout"); }

            /**
             * Returns the version associated to this alias: databinding (8.0.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getDatabinding() { return getVersion("databinding"); }

            /**
             * Returns the version associated to this alias: datastorePreferences (1.0.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getDatastorePreferences() { return getVersion("datastorePreferences"); }

            /**
             * Returns the version associated to this alias: facebookLogin (sdk-version-16.0.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getFacebookLogin() { return getVersion("facebookLogin"); }

            /**
             * Returns the version associated to this alias: firebaseAuth (22.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getFirebaseAuth() { return getVersion("firebaseAuth"); }

            /**
             * Returns the version associated to this alias: firebaseBom (32.2.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getFirebaseBom() { return getVersion("firebaseBom"); }

            /**
             * Returns the version associated to this alias: firebaseCommonKtx (20.3.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getFirebaseCommonKtx() { return getVersion("firebaseCommonKtx"); }

            /**
             * Returns the version associated to this alias: firebaseDatabaseKtx (20.2.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getFirebaseDatabaseKtx() { return getVersion("firebaseDatabaseKtx"); }

            /**
             * Returns the version associated to this alias: firebaseFessaging (17.0.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getFirebaseFessaging() { return getVersion("firebaseFessaging"); }

            /**
             * Returns the version associated to this alias: firebaseFessagingKtx (23.2.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getFirebaseFessagingKtx() { return getVersion("firebaseFessagingKtx"); }

            /**
             * Returns the version associated to this alias: firebaseFirestoreKtx (24.7.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getFirebaseFirestoreKtx() { return getVersion("firebaseFirestoreKtx"); }

            /**
             * Returns the version associated to this alias: glide (4.15.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getGlide() { return getVersion("glide"); }

            /**
             * Returns the version associated to this alias: glideTransformations (4.0.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getGlideTransformations() { return getVersion("glideTransformations"); }

            /**
             * Returns the version associated to this alias: gson (2.8.7)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getGson() { return getVersion("gson"); }

            /**
             * Returns the version associated to this alias: hilt (2.45)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getHilt() { return getVersion("hilt"); }

            /**
             * Returns the version associated to this alias: hiltExt (1.0.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getHiltExt() { return getVersion("hiltExt"); }

            /**
             * Returns the version associated to this alias: i18nLanguagePack (1.0.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getI18nLanguagePack() { return getVersion("i18nLanguagePack"); }

            /**
             * Returns the version associated to this alias: junit (1.1.5)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJunit() { return getVersion("junit"); }

            /**
             * Returns the version associated to this alias: junit4 (4.13.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJunit4() { return getVersion("junit4"); }

            /**
             * Returns the version associated to this alias: kotlin (1.8.21)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlin() { return getVersion("kotlin"); }

            /**
             * Returns the version associated to this alias: kotlinxCoroutines (1.6.4)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlinxCoroutines() { return getVersion("kotlinxCoroutines"); }

            /**
             * Returns the version associated to this alias: kotlinxDatetime (0.4.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlinxDatetime() { return getVersion("kotlinxDatetime"); }

            /**
             * Returns the version associated to this alias: kotlinxSerializationJson (1.5.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlinxSerializationJson() { return getVersion("kotlinxSerializationJson"); }

            /**
             * Returns the version associated to this alias: ktor (2.2.4)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKtor() { return getVersion("ktor"); }

            /**
             * Returns the version associated to this alias: lifecycleCommon (2.6.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getLifecycleCommon() { return getVersion("lifecycleCommon"); }

            /**
             * Returns the version associated to this alias: lint (31.0.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getLint() { return getVersion("lint"); }

            /**
             * Returns the version associated to this alias: material (1.9.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMaterial() { return getVersion("material"); }

            /**
             * Returns the version associated to this alias: moshi (1.14.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMoshi() { return getVersion("moshi"); }

            /**
             * Returns the version associated to this alias: multidex (1.0.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMultidex() { return getVersion("multidex"); }

            /**
             * Returns the version associated to this alias: navigationFragment (2.6.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getNavigationFragment() { return getVersion("navigationFragment"); }

            /**
             * Returns the version associated to this alias: navigationRuntimeKtx (2.6.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getNavigationRuntimeKtx() { return getVersion("navigationRuntimeKtx"); }

            /**
             * Returns the version associated to this alias: pagingCommonKtx (3.1.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getPagingCommonKtx() { return getVersion("pagingCommonKtx"); }

            /**
             * Returns the version associated to this alias: pagingCompose (3.2.0-beta01)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getPagingCompose() { return getVersion("pagingCompose"); }

            /**
             * Returns the version associated to this alias: pagingRuntime (3.1.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getPagingRuntime() { return getVersion("pagingRuntime"); }

            /**
             * Returns the version associated to this alias: playServicesAuth (19.0.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getPlayServicesAuth() { return getVersion("playServicesAuth"); }

            /**
             * Returns the version associated to this alias: securityCrypto (1.0.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getSecurityCrypto() { return getVersion("securityCrypto"); }

            /**
             * Returns the version associated to this alias: shimmer (0.5.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getShimmer() { return getVersion("shimmer"); }

            /**
             * Returns the version associated to this alias: swiperefreshlayout (1.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getSwiperefreshlayout() { return getVersion("swiperefreshlayout"); }

            /**
             * Returns the version associated to this alias: threeTenABP (1.4.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getThreeTenABP() { return getVersion("threeTenABP"); }

            /**
             * Returns the version associated to this alias: timber (5.0.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTimber() { return getVersion("timber"); }

            /**
             * Returns the version associated to this alias: viewPump (2.0.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getViewPump() { return getVersion("viewPump"); }

        /**
         * Returns the group of versions at versions.org
         */
        public OrgVersionAccessors getOrg() { return vaccForOrgVersionAccessors; }

    }

    public static class OrgVersionAccessors extends VersionFactory  {

        private final OrgJetbrainsVersionAccessors vaccForOrgJetbrainsVersionAccessors = new OrgJetbrainsVersionAccessors(providers, config);
        public OrgVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.jetbrains
         */
        public OrgJetbrainsVersionAccessors getJetbrains() { return vaccForOrgJetbrainsVersionAccessors; }

    }

    public static class OrgJetbrainsVersionAccessors extends VersionFactory  {

        private final OrgJetbrainsKotlinVersionAccessors vaccForOrgJetbrainsKotlinVersionAccessors = new OrgJetbrainsKotlinVersionAccessors(providers, config);
        public OrgJetbrainsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.jetbrains.kotlin
         */
        public OrgJetbrainsKotlinVersionAccessors getKotlin() { return vaccForOrgJetbrainsKotlinVersionAccessors; }

    }

    public static class OrgJetbrainsKotlinVersionAccessors extends VersionFactory  {

        public OrgJetbrainsKotlinVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.jetbrains.kotlin.android (1.8.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroid() { return getVersion("org.jetbrains.kotlin.android"); }

    }

    public static class BundleAccessors extends BundleFactory {
        private final PlayBundleAccessors baccForPlayBundleAccessors = new PlayBundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

            /**
             * Creates a dependency bundle provider for facebook which is an aggregate for the following dependencies:
             * <ul>
             *    <li>com.facebook.shimmer:shimmer</li>
             *    <li>com.facebook.android:facebook-login</li>
             * </ul>
             * This bundle was declared in catalog libs.versions.toml
             */
            public Provider<ExternalModuleDependencyBundle> getFacebook() { return createBundle("facebook"); }

            /**
             * Creates a dependency bundle provider for firebasse which is an aggregate for the following dependencies:
             * <ul>
             *    <li>com.google.firebase:firebase-auth</li>
             *    <li>com.google.firebase:firebase-common-ktx</li>
             *    <li>com.google.firebase:firebase-database-ktx</li>
             *    <li>com.google.firebase:firebase-messaging</li>
             *    <li>com.google.firebase:firebase-messaging-ktx</li>
             *    <li>com.google.firebase:firebase-firestore-ktx</li>
             * </ul>
             * This bundle was declared in catalog libs.versions.toml
             */
            public Provider<ExternalModuleDependencyBundle> getFirebasse() { return createBundle("firebasse"); }

            /**
             * Creates a dependency bundle provider for ktor which is an aggregate for the following dependencies:
             * <ul>
             *    <li>io.ktor:ktor-client-content-negotiation</li>
             *    <li>io.ktor:ktor-client-core</li>
             *    <li>io.ktor:ktor-client-logging</li>
             *    <li>io.ktor:ktor-client-okhttp</li>
             *    <li>io.ktor:ktor-client-resources</li>
             *    <li>io.ktor:ktor-serialization-kotlinx-json</li>
             *    <li>io.ktor:ktor-client-auth</li>
             * </ul>
             * This bundle was declared in catalog libs.versions.toml
             */
            public Provider<ExternalModuleDependencyBundle> getKtor() { return createBundle("ktor"); }

            /**
             * Creates a dependency bundle provider for moshi which is an aggregate for the following dependencies:
             * <ul>
             *    <li>com.squareup.moshi:moshi-kotlin</li>
             *    <li>com.squareup.moshi:moshi-kotlin-codegen</li>
             * </ul>
             * This bundle was declared in catalog libs.versions.toml
             */
            public Provider<ExternalModuleDependencyBundle> getMoshi() { return createBundle("moshi"); }

            /**
             * Creates a dependency bundle provider for viewmodel which is an aggregate for the following dependencies:
             * <ul>
             *    <li>androidx.lifecycle:lifecycle-livedata-ktx</li>
             *    <li>androidx.lifecycle:lifecycle-runtime-ktx</li>
             *    <li>androidx.lifecycle:lifecycle-viewmodel-savedstate</li>
             *    <li>androidx.lifecycle:lifecycle-compiler</li>
             *    <li>androidx.lifecycle:lifecycle-common-java8</li>
             *    <li>androidx.lifecycle:lifecycle-service</li>
             *    <li>androidx.lifecycle:lifecycle-process</li>
             *    <li>androidx.lifecycle:lifecycle-reactivestreams-ktx</li>
             *    <li>androidx.lifecycle:lifecycle-runtime-testing</li>
             * </ul>
             * This bundle was declared in catalog libs.versions.toml
             */
            public Provider<ExternalModuleDependencyBundle> getViewmodel() { return createBundle("viewmodel"); }

            /**
             * Creates a dependency bundle provider for youtube which is an aggregate for the following dependencies:
             * <ul>
             *    <li>com.pierfrancescosoffritti.androidyoutubeplayer:core</li>
             *    <li>androidx.work:work-runtime-ktx</li>
             * </ul>
             * This bundle was declared in catalog libs.versions.toml
             */
            public Provider<ExternalModuleDependencyBundle> getYoutube() { return createBundle("youtube"); }

        /**
         * Returns the group of bundles at bundles.play
         */
        public PlayBundleAccessors getPlay() { return baccForPlayBundleAccessors; }

    }

    public static class PlayBundleAccessors extends BundleFactory {

        public PlayBundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

            /**
             * Creates a dependency bundle provider for play.services which is an aggregate for the following dependencies:
             * <ul>
             *    <li>com.google.android.gms:play-services-auth</li>
             *    <li>com.google.android.gms:play-services-location</li>
             * </ul>
             * This bundle was declared in catalog libs.versions.toml
             */
            public Provider<ExternalModuleDependencyBundle> getServices() { return createBundle("play.services"); }

    }

    public static class PluginAccessors extends PluginFactory {
        private final AndroidPluginAccessors paccForAndroidPluginAccessors = new AndroidPluginAccessors(providers, config);
        private final KotlinPluginAccessors paccForKotlinPluginAccessors = new KotlinPluginAccessors(providers, config);
        private final NavigationPluginAccessors paccForNavigationPluginAccessors = new NavigationPluginAccessors(providers, config);
        private final OrgPluginAccessors paccForOrgPluginAccessors = new OrgPluginAccessors(providers, config);

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for hilt to the plugin id 'com.google.dagger.hilt.android'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getHilt() { return createPlugin("hilt"); }

        /**
         * Returns the group of plugins at plugins.android
         */
        public AndroidPluginAccessors getAndroid() { return paccForAndroidPluginAccessors; }

        /**
         * Returns the group of plugins at plugins.kotlin
         */
        public KotlinPluginAccessors getKotlin() { return paccForKotlinPluginAccessors; }

        /**
         * Returns the group of plugins at plugins.navigation
         */
        public NavigationPluginAccessors getNavigation() { return paccForNavigationPluginAccessors; }

        /**
         * Returns the group of plugins at plugins.org
         */
        public OrgPluginAccessors getOrg() { return paccForOrgPluginAccessors; }

    }

    public static class AndroidPluginAccessors extends PluginFactory {

        public AndroidPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for android.application to the plugin id 'com.android.application'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getApplication() { return createPlugin("android.application"); }

            /**
             * Creates a plugin provider for android.library to the plugin id 'com.android.library'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getLibrary() { return createPlugin("android.library"); }

    }

    public static class KotlinPluginAccessors extends PluginFactory {

        public KotlinPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for kotlin.jvm to the plugin id 'org.jetbrains.kotlin.jvm'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getJvm() { return createPlugin("kotlin.jvm"); }

            /**
             * Creates a plugin provider for kotlin.serialization to the plugin id 'org.jetbrains.kotlin.plugin.serialization'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getSerialization() { return createPlugin("kotlin.serialization"); }

    }

    public static class NavigationPluginAccessors extends PluginFactory {

        public NavigationPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for navigation.safeargs to the plugin id 'androidx.navigation.safeargs.kotlin'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getSafeargs() { return createPlugin("navigation.safeargs"); }

    }

    public static class OrgPluginAccessors extends PluginFactory {
        private final OrgJetbrainsPluginAccessors paccForOrgJetbrainsPluginAccessors = new OrgJetbrainsPluginAccessors(providers, config);

        public OrgPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of plugins at plugins.org.jetbrains
         */
        public OrgJetbrainsPluginAccessors getJetbrains() { return paccForOrgJetbrainsPluginAccessors; }

    }

    public static class OrgJetbrainsPluginAccessors extends PluginFactory {
        private final OrgJetbrainsKotlinPluginAccessors paccForOrgJetbrainsKotlinPluginAccessors = new OrgJetbrainsKotlinPluginAccessors(providers, config);

        public OrgJetbrainsPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of plugins at plugins.org.jetbrains.kotlin
         */
        public OrgJetbrainsKotlinPluginAccessors getKotlin() { return paccForOrgJetbrainsKotlinPluginAccessors; }

    }

    public static class OrgJetbrainsKotlinPluginAccessors extends PluginFactory {

        public OrgJetbrainsKotlinPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for org.jetbrains.kotlin.android to the plugin id 'org.jetbrains.kotlin.android'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getAndroid() { return createPlugin("org.jetbrains.kotlin.android"); }

            /**
             * Creates a plugin provider for org.jetbrains.kotlin.kapt to the plugin id 'org.jetbrains.kotlin.kapt'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getKapt() { return createPlugin("org.jetbrains.kotlin.kapt"); }

    }

}
