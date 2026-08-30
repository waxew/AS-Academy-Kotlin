# Kotlin 1.2.0 Release Status

## Completed

- Course schema and stable IDs validated.
- 4 levels, 12 chapters and 46 lessons packaged.
- Advanced and Specialist curriculum received deep production-oriented passes.
- 22 exercises include acceptance criteria.
- 30 quiz questions include explanations and advanced/specialist scenarios.
- 8 projects culminate in a Production Capstone.
- Canonical solution/rubric material is available under `course/solutions`.
- Complete `course/` content is copied into Android assets by CI.
- Android lint, unit tests, Debug APK and Release Candidate APK/AAB are CI quality gates.
- SHA-256 files are generated for build artifacts.
- Android app version is aligned to `1.2.0` with monotonic `versionCode = 12000`.
- Release signing configuration is external to source control to preserve the permanent update identity.

## Shared Core responsibilities

The Kotlin repository intentionally does not duplicate common Academy implementation. `AS-Academy-Core` owns navigation, RTL shell/drawer, persistence, progress, quiz history, exercises/projects, search, bookmarks, notes, glossary, settings, backup/update infrastructure and the code-runner framework. Kotlin supplies the course package and thin Android entry point.

## Release boundary

A final publish-signed APK/AAB cannot be produced safely until the permanent release keystore identity is supplied to the secure build environment. A temporary or generated replacement key must not be used because future updates signed by another identity would not install over the published application.

Required external values:

- `AS_RELEASE_STORE_FILE`
- `AS_RELEASE_STORE_PASSWORD`
- `AS_RELEASE_KEY_ALIAS`
- `AS_RELEASE_KEY_PASSWORD`

After these values point to the permanent keystore, the existing release build path signs with that identity. Signature verification and final publish checksum must be performed against that signed artifact.

## Definition of done

Course/content development for milestone 1.2.0 is considered complete when validation and Android CI pass for the final source commit. Store publication is a deployment operation and remains gated only by the permanent signing identity and any store-specific credentials/metadata.
