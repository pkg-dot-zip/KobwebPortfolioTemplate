This is a [Kobweb](https://github.com/varabyte/kobweb) project bootstrapped with the [KobwebPortfolioTemplate](https://github.com/pkg-dot-zip/KobwebPortfolioTemplate).

## Getting Started
For more detailed instructions make sure to check out the repository page [here](https://github.com/pkg-dot-zip/KobwebPortfolioTemplate).

First, run the development server by typing the following command in a terminal under the `site` folder:

```bash
$ cd site
$ kobweb run
```

Open [http://localhost:8080](http://localhost:8080) with your browser to see the result.

You can use any editor you want for the project, but we recommend using **IntelliJ IDEA Community Edition** downloaded
using the [Toolbox App](https://www.jetbrains.com/toolbox-app/).

Press `Q` in the terminal to gracefully stop the server.

### Live Reload

While Kobweb is running, feel free to modify the code! When you make any changes, Kobweb will notice this
automatically, and the site will indicate the status of the build and automatically reload when ready.

## Exporting the Project

When you are ready to ship, you should shutdown the development server and then export the project using:

```bash
$ kobweb export --layout static
```