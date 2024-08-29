# BlockBridge Fabric Mod #
BlockBridge is a Utility/Api for connecting Minecraft with Discord Servers

## Prerequisites ##
In order to run the mod you will need the following:
1) A BlockBridge Discord bot. ([Check out how to get one here](https://github.com/Block-Bridge/block_bridge_discord))
2) A Fabric Minecraft Server. ([Check out how to get one here](https://fabricmc.net/))

## Installation ##
This mod is built for server environments only, so you will need to install it on your server.

#### Download the latest release from the [Releases](https://ci.vanillaflux.com)
Download the latest stable release from the releases page.

#### Install the mod
Upload the mod jar file to the mods folder of your server. Verify that the mod jar file is in the mods folder.

#### Start the server
On first launch you should generate a bunch of errors, this is normal. The mod will generate a config file and then stop. Edit the config file.

#### Editing the config file
The config file will be located in the config folder of your server, and will be named `blockbridge.json`. The config file will look like this:
```json
{
  "app_version": "v1",
  "api_url": "http://localhost:8585",
  "api_entry_point": "/api",
  "app_entry_point": "/app"
}
```
* `api_url`: The url of your BlockBridge Discord bot. If this is behind a virtual host, you don't need to include the port.
* `app_version`: The version of the BlockBridge Discord bot. Do not edit this.
* `api_entry_point` & `app_entry_point`: The entry points for the api and app sections of the BlockBridge Discord bot.You will need to edit them to mirror your discord bot config.

#### Launch the server again
Once you have edited the config file, start the server again. The mod should now be running.