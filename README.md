# W3 Assignment - Twitter Client

Twitter Client is an android app that supports viewing a Twitter timeline and composing a new tweet.

Submitted by: Tan Le Ngoc Minh

Time spent: 25 hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can sign in to Twitter using OAuth login
* [x] User can view the tweets from their home timeline:
 - User should be displayed the username, name, and body for each tweet
 - User should be displayed the relative timestamp for each tweet "8m", "7h"
 - User can view more tweets as they scroll with infinite pagination
* [x] User can compose a new tweet
 - User can click a “Compose” icon in the Action Bar on the top right
 - User can then enter a new tweet and post this to twitter
 - User is taken back to home timeline with **new tweet visible** in timeline

The following **optional** features are implemented:

* [x] While composing a tweet, user can see a character counter with characters remaining for tweet out of 140
* [x] Links in tweets are clickable and will launch the web browser
* [x] User can refresh tweets timeline by pulling down to refresh
* [x] User can open the twitter app offline and see last loaded tweets
* [ ] User can tap a tweet to display a "detailed" view of that tweet
* [x] User can select "reply" from detail view to respond to a tweet
* [x] Improve the user interface and theme the app to feel "twitter branded" 
* [x] Compose activity is replaced with a modal overlay 
* [x] Use Parcelable instead of Serializable using the popular Parceler library
* [x] Apply the popular Butterknife annotation library to reduce view boilerplate
* [x] Leverage RecyclerView as a replacement for the ListView and ArrayAdapter for all lists of tweets
* [ ] Move the "Compose" action to a FloatingActionButton instead of on the AppBar
* [ ] Replace Picasso with Glide for more efficient image rendering

The following **advanced** feature is implemented:
* [x] Apply MVP pattern

## Video Walkthrough 

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/mhs64IZ.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.

## License

    Copyright 2016 TanLNM

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.