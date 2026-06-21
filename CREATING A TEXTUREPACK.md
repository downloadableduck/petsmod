# Creating a PetsMod texturepack

Creating a PetsMod texturepack is fairly simple, but let's go over some things first.

- Make sure to have [BlockBench](https://www.blockbench.net/) installed. (The [web app](https://web.blockbench.net/)
  will work too.)
- Open [PetsMod's GitHub page](https://github.com/downloadableduck/petsmod) (you probably already have it open, but just
  keep it there.)
- This guide is for creating textures for the **custom** entities found in PetsMod, not the vanilla ones. If you are
  trying to modify the vanilla Pet textures, just make a normal resource pack and it'll work just fine.
- This is for changing the existing textures, not adding new ones. Refer to CREATING A CUSTOM PET.md for information on
  that.
- This guide assums that you already know the basics of making texturepacks.

Let's get started!

### Downloading the Models

All of PetsMod's custom models are available in the GitHub repository. Navigate to the models folder, and all the
.bbmodel files should be there.

Now, go ahead and re-texture the entity. Save the PNG. Depending on which entity and which skins you are targeting, the
filepath will be different, but the general path will be
`assets/pets-mod/textures/entity/[entity name]/[entity skin.png]`

Below is a list of all of the texture paths as of 0.8.0-dev. If something isn't working, or the file you want to place
isn't below, you can check
the [GitHub repositories' entity textures folder](https://github.com/downloadableduck/petsmod/tree/26.1.x/src/main/resources/assets/pets/textures/entity)
for everything you need.

<details>
<summary>Paths</summary>

**Duck (male mallard):** `assets/pets-mod/textures/entity/duck/mallard_male.png`

**Duck (pekin):** `assets/pets-mod/textures/entity/duck/pekin.png`

**Duck (rubber):** `assets/pets-mod/textures/entity/duck/rubber.png`

**Racoon (normal):** `assets/pets-mod/textures/entity/racoon/racoon.png`

**Racoon (albino):** `assets/pets-mod/textures/entity/racoon/albino.png`

**Penguin:** `assets/pets-mod/textures/entity/penguin/penguin.png`

**Dumbo Octopus:** `assets/pets-mod/textures/entity/dumbo_octopus/[color.png]`

(Possible colors: blue, green, orange, pink, red, yellow)

**Koi:** `assets/pets-mod/textures/entity/koi/koi.png`

**Stingray:** `assets/pets-mod/textures/entity/stingray/stingray.png`

</details>